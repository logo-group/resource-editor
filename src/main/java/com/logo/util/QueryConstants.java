package com.logo.util;

public class QueryConstants {

	public static final String SEARCHBYPARAM = " select rr.* from RE_RESOURCEITEMS rr CROSS APPLY dbo.RE_RESOURCESFUNC(rr.ID,rr.RESOURCEREF,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " ORDER BY rr.RESOURCEREF ASC, rr.ORDERNR ASC \n-- #pageable\n";

	public static final String SEARCHBYPARAMCOUNT = " select count(*) from RE_RESOURCEITEMS rr CROSS APPLY dbo.RE_RESOURCESFUNC(rr.ID,rr.RESOURCEREF,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})";

	public static final String SEARCHBYPARAMALL = " select rr.* from RE_RESOURCEITEMS rr CROSS APPLY dbo.RE_RESOURCESFUNC(rr.ID,rr.RESOURCEREF,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " CROSS APPLY dbo.RE_RESOURCEITEMFUNC(rr.ID, :#{#searchParam.orderNrBegin},:#{#searchParam.orderNrEnd},"
			+ ":#{#searchParam.tagNrBegin},:#{#searchParam.tagNrEnd},:#{#searchParam.levelNrBegin},:#{#searchParam.levelNrEnd},"
			+ ":#{#searchParam.prefixFlag},:#{#searchParam.infoFlag},:#{#searchParam.resourceItemCaseFlag},"
			+ ":#{#searchParam.prefixComboText},:#{#searchParam.infoComboText})"
			+ " ORDER BY rr.RESOURCEREF ASC, rr.ORDERNR ASC \n-- #pageable\n";

	public static final String SEARCHBYPARAMALLCOUNT = " select count(*) from RE_RESOURCEITEMS rr CROSS APPLY dbo.RE_RESOURCESFUNC(rr.ID,rr.RESOURCEREF,"
			+ ":#{#searchParam.resNrBegin},:#{#searchParam.resNrEnd},:#{#searchParam.descFlag},:#{#searchParam.resGrpFlag},"
			+ ":#{#searchParam.resTypFlag},:#{#searchParam.resCaseFlag},:#{#searchParam.resStateFlag},:#{#searchParam.word})"
			+ " CROSS APPLY dbo.RE_RESOURCEITEMFUNC(rr.ID, :#{#searchParam.orderNrBegin},:#{#searchParam.orderNrEnd},"
			+ ":#{#searchParam.tagNrBegin},:#{#searchParam.tagNrEnd},:#{#searchParam.levelNrBegin},:#{#searchParam.levelNrEnd},"
			+ ":#{#searchParam.prefixFlag},:#{#searchParam.infoFlag},:#{#searchParam.resourceItemCaseFlag},"
			+ ":#{#searchParam.prefixComboText},:#{#searchParam.infoComboText})";

	public static final String LOCCHARTQUERY = " select count(rr.ID) as rcount,count(rt.ID) as trcount,count(us.ID) as encount from RE_RESOURCEITEMS rr"
			+ " left outer join RE_TURKISHTR rt on(rr.ID = rt.RESOURCEITEMREF)"
			+ " left outer join RE_ENGLISHUS us on(rr.ID = us.RESOURCEITEMREF)"
			+ " where rr.RESOURCEREF in (select ID from RE_RESOURCES where RESOURCENR = ?1 and RESOURCEGROUP = ?2) ";

	public static final String RESGROUPCOUNTQUERY = " select RESOURCEGROUP as resourcegroup, cast(count(*) as float) as cnt from RE_RESOURCES group by RESOURCEGROUP";

	public static final String LANGCOUNTQUERY = " select cast(count(ID) as float) as cnt from RE_TURKISHTR union all"
			+ " select cast(count(ID) as float) as cnt from RE_ALBANIANKV union all"
			+ " select cast(count(ID) as float) as cnt from RE_ARABICEG union all"
			+ " select cast(count(ID) as float) as cnt from RE_ARABICJO union all"
			+ " select cast(count(ID) as float) as cnt from RE_ARABICSA union all"
			+ " select cast(count(ID) as float) as cnt from RE_AZERBAIJANIAZ union all"
			+ " select cast(count(ID) as float) as cnt from RE_BULGARIANBG union all"
			+ " select cast(count(ID) as float) as cnt from RE_ENGLISHUS union all"
			+ " select cast(count(ID) as float) as cnt from RE_FRENCHFR union all"
			+ " select cast(count(ID) as float) as cnt from RE_GEORGIANGE union all"
			+ " select cast(count(ID) as float) as cnt from RE_GERMANDE union all"
			+ " select cast(count(ID) as float) as cnt from RE_PERSIANIR union all"
			+ " select cast(count(ID) as float) as cnt from RE_ROMANIANRO union all"
			+ " select cast(count(ID) as float) as cnt from RE_RUSSIANRU union all"
			+ " select cast(count(ID) as float) as cnt from RE_TURKMENTM ";

	private QueryConstants() {
	}
}
