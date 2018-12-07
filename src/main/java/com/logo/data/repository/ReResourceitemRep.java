package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.logo.data.entity.ReResourceitem;
import com.logo.util.QueryConstants;
import com.logo.util.search.SearchParam;

@Component
@Transactional(readOnly = true)
public interface ReResourceitemRep extends JpaRepository<ReResourceitem, Long> {

	@Query(value = "select ri from ReResourceitem ri where resourceref = :resourceref and tagnr > -1000000")
	List<ReResourceitem> findByresourcerefEquals(@Param("resourceref") Integer resourceref);

	@Query(value = "select max(TAGNR) from RE_RESOURCEITEMS with(nolock) where RESOURCEREF = :resourceref", nativeQuery = true)
	Integer getMaxTagNr(@Param("resourceref") Integer resourceref);

	@Query(value = "select max(ORDERNR) from RE_RESOURCEITEMS with(nolock) where RESOURCEREF = :resourceref", nativeQuery = true)
	Integer getMaxOrderNr(@Param("resourceref") Integer resourceref);

	@Query(value = "select * from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',:searchTerm,'%')) ORDER BY RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',:searchTerm,'%'))", nativeQuery = true)
	Page<ReResourceitem> searchByresourcereTR(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query(value = "select * from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',:searchTerm,'%')) ORDER BY CREATEDON DESC, RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',:searchTerm,'%'))", nativeQuery = true)
	Page<ReResourceitem> getAllResItems(@Param("searchTerm") String searchTerm, Pageable pageable);

	@Query(value = "select * from RE_RESOURCEITEMS where RESOURCEREF in (select ID from RE_RESOURCES where RESOURCENR = :resNr AND RESOURCEGROUP = :resGrp) ORDER BY RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where RESOURCEREF in (select ID from RE_RESOURCES where RESOURCENR = :resNr AND RESOURCEGROUP = :resGrp)", nativeQuery = true)
	Page<ReResourceitem> searchByresourceNr(Pageable pageable, @Param("resNr") String resNr,
			@Param("resGrp") String resGrp);

	@Query(value = QueryConstants.SEARCHBYPARAM, countQuery = QueryConstants.SEARCHBYPARAMCOUNT, nativeQuery = true)
	Page<ReResourceitem> searchByParam(Pageable pageable, @Param("searchParam") SearchParam searchParam);

	@Query(value = QueryConstants.SEARCHBYPARAMALL, countQuery = QueryConstants.SEARCHBYPARAMALLCOUNT, nativeQuery = true)
	Page<ReResourceitem> searchByParamAll(Pageable pageable, @Param("searchParam") SearchParam searchParam);

	@Query(value = QueryConstants.LOCCHARTQUERY, nativeQuery = true)
	<T> List<T> getCountForChart(Integer resNr, String resGrp);

}