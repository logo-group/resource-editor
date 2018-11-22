package com.logo.util;

public class QueryConstants {

	public static final String SEARCHQUERY1 = " select * from re_resourceitems where resourceref in"
			+ " (select id from re_resources where (resourcenr between ?1 and ?2) and (resourcegroup in ?3)"
			+ " and (resourcetype in ?4) and (resourcecase in ?5) and (active in ?6)  )"
			+ " ORDER BY resourceref ASC, ordernr ASC \n-- #pageable\n";
	
	public static final String SEARCHCOUNT1 = " select count(*) from re_resourceitems where resourceref in"
			+ " (select id from re_resources where (resourcenr between ?1 and ?2) and (resourcegroup in ?3)"
			+ " and (resourcetype in ?4) and (resourcecase in ?5) and (active in ?6) )";

	public static final String SEARCHQUERY2 = " select rr.* from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,?1,?2,3,-1,-1,-1,-1,'')"
			+ " ORDER BY rr.resourceref ASC, rr.ordernr ASC \n-- #pageable\n";
	
	public static final String SEARCHCOUNT2 = " select count(*) from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,?1,?2,3,-1,-1,-1,-1,'')";

	public static final String SEARCHBYPARAM = " select rr.* from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " ORDER BY rr.resourceref ASC, rr.ordernr ASC \n-- #pageable\n";
	
	public static final String SEARCHBYPARAMCOUNT = " select count(*) from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})";

	public static final String SEARCHBYPARAMALL = " select rr.* from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " CROSS APPLY dbo.RE_RESOURCEITEMFUNC(rr.id, :#{#searchParam.orderNrBegin},:#{#searchParam.orderNrEnd},"
			+ ":#{#searchParam.tagNrBegin},:#{#searchParam.tagNrEnd},:#{#searchParam.levelNrBegin},:#{#searchParam.levelNrEnd},"
			+ ":#{#searchParam.prefixFlag},:#{#searchParam.infoFlag},:#{#searchParam.resourceItemCaseFlag},"
			+ ":#{#searchParam.prefixComboText},:#{#searchParam.infoComboText})" 
			+ " ORDER BY rr.resourceref ASC, rr.ordernr ASC \n-- #pageable\n";
	
	public static final String SEARCHBYPARAMALLCOUNT = " select count(*) from re_resourceitems rr CROSS APPLY dbo.re_resourcesFunc(rr.id,rr.resourceref,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " CROSS APPLY dbo.RE_RESOURCEITEMFUNC(rr.id, :#{#searchParam.orderNrBegin},:#{#searchParam.orderNrEnd},"
			+ ":#{#searchParam.tagNrBegin},:#{#searchParam.tagNrEnd},:#{#searchParam.levelNrBegin},:#{#searchParam.levelNrEnd},"
			+ ":#{#searchParam.prefixFlag},:#{#searchParam.infoFlag},:#{#searchParam.resourceItemCaseFlag},"
			+ ":#{#searchParam.prefixComboText},:#{#searchParam.infoComboText})";

	public static final String LOCCHARTQUERY = " select count(rr.id) as rcount,count(rt.id) as trcount,count(us.id) as encount from re_resourceitems rr"
			+ " left outer join re_turkishtr rt on(rr.id = rt.resourceitemref)"
			+ " left outer join re_englishus us on(rr.id = us.resourceitemref)"
			+ " where rr.resourceref in (select id from re_resources where resourcenr = ?1 and resourcegroup = ?2) ";
	
	public static final String RESGROUPCOUNTQUERY = " select resourcegroup as resourcegroup, cast(count(*) as float) as cnt from re_resources group by resourcegroup";
	
	public static final String LANGCOUNTQUERY = " select cast(count(id) as float) as cnt from re_turkishtr union all"
			+ " select cast(count(id) as float) as cnt from re_albaniankv union all"
			+ " select cast(count(id) as float) as cnt from re_arabiceg union all"
			+ " select cast(count(id) as float) as cnt from re_arabicjo union all"
			+ " select cast(count(id) as float) as cnt from re_arabicsa union all"
			+ " select cast(count(id) as float) as cnt from re_azerbaijaniaz union all"
			+ " select cast(count(id) as float) as cnt from re_bulgarianbg union all"
			+ " select cast(count(id) as float) as cnt from re_englishus union all"
			+ " select cast(count(id) as float) as cnt from re_frenchfr union all"
			+ " select cast(count(id) as float) as cnt from re_georgiange union all"
			+ " select cast(count(id) as float) as cnt from re_germande union all"
			+ " select cast(count(id) as float) as cnt from re_persianir union all"
			+ " select cast(count(id) as float) as cnt from re_romanianro union all"
			+ " select cast(count(id) as float) as cnt from re_russianru union all"
			+ " select cast(count(id) as float) as cnt from re_turkmentm ";

	private QueryConstants() {
	}
}
