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

	List<ReResourceitem> findAllBy(Pageable pageable);

	List<ReResourceitem> findByresourcerefEqualsOrderByOrdernrAsc(Integer resourceref);

	@Query(value = "select * from RE_RESOURCEITEMS with(nolock) where RESOURCEREF = ?1", nativeQuery = true)
	List<ReResourceitem> findByresourcerefEquals(Integer resourceref);

	@Query(value = "select max(TAGNR) from RE_RESOURCEITEMS with(nolock) where RESOURCEREF = ?1", nativeQuery = true)
	Integer getMaxTagNr(Integer resourceref);

	@Query(value = "select max(ORDERNR) from RE_RESOURCEITEMS with(nolock) where RESOURCEREF = ?1", nativeQuery = true)
	Integer getMaxOrderNr(Integer resourceref);

	@Query(value = "select * from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',?1,'%')) ORDER BY RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',?1,'%'))", nativeQuery = true)
	Page<ReResourceitem> searchByresourcereTR(String searchTerm, Pageable pageable);

	@Query(value = "select * from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',?1,'%')) ORDER BY CREATEDON DESC, RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where ID in(select RESOURCEITEMREF from RE_TURKISHTR where RESOURCESTR LIKE CONCAT('%',?1,'%'))", nativeQuery = true)
	Page<ReResourceitem> getAllResItems(String searchTerm, Pageable pageable);

	@Query(value = "select * from RE_RESOURCEITEMS where RESOURCEREF in (select ID from RE_RESOURCES where RESOURCENR = ?1 AND RESOURCEGROUP = ?2) ORDER BY RESOURCEREF ASC, ORDERNR ASC \n-- #pageable\n", countQuery = "select count(*) from RE_RESOURCEITEMS where RESOURCEREF in (select ID from RE_RESOURCES where RESOURCENR = ?1 AND RESOURCEGROUP = ?2)", nativeQuery = true)
	Page<ReResourceitem> searchByresourceNr(Pageable pageable, String resNr, String resGrp);

	@Query(value = QueryConstants.SEARCHQUERY1, countQuery = QueryConstants.SEARCHCOUNT1, nativeQuery = true)
	Page<ReResourceitem> search1(Pageable pageable, String resourcenr1, String resourcenr2, List<String> resourcegroup,
			List<Integer> resourcetype, List<Integer> resourcecase, List<Integer> active);

	@Query(value = QueryConstants.SEARCHQUERY2, countQuery = QueryConstants.SEARCHCOUNT2, nativeQuery = true)
	Page<ReResourceitem> search2(Pageable pageable, String resourcenr1, String resourcenr2);

	@Query(value = QueryConstants.SEARCHBYPARAM, countQuery = QueryConstants.SEARCHBYPARAMCOUNT, nativeQuery = true)
	Page<ReResourceitem> searchByParam(Pageable pageable, @Param("searchParam") SearchParam searchParam);

	@Query(value = QueryConstants.SEARCHBYPARAMALL, countQuery = QueryConstants.SEARCHBYPARAMALLCOUNT, nativeQuery = true)
	Page<ReResourceitem> searchByParamAll(Pageable pageable, @Param("searchParam") SearchParam searchParam);

	@Query(value = QueryConstants.LOCCHARTQUERY, nativeQuery = true)
	<T> List<T> getCountForChart(Integer resNr, String resGrp);

}