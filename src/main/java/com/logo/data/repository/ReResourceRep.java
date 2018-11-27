package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.logo.data.entity.ReResource;
import com.logo.util.QueryConstants;

@Component
@Transactional(readOnly = true)
public interface ReResourceRep extends JpaRepository<ReResource, Long> {

	List<ReResource> findAllBy(Pageable pageable);

	@Query(value = "select * from RE_RESOURCES with(nolock) where ID = ?1", nativeQuery = true)
	ReResource findByid(Integer ref);

	@Query(value = "select TOP 1 * from RE_RESOURCES with(nolock) where RESOURCENR = ?1 AND RESOURCEGROUP = ?2", nativeQuery = true)
	ReResource findByresourceNr(Integer resourceNr, String resourcegroup);

	@Query(value = "select * from RE_RESOURCES with(nolock) where RESOURCENR between ?1 and ?2", nativeQuery = true)
	List<ReResource> findByresourceNrEquals(Integer resourceNr1, Integer resourceNr2);

	@Query(value = "select TOP 10 LTRIM(CONCAT(RESOURCEGROUP,'->',RESOURCENR)) from RE_RESOURCES with(nolock) where STR(RESOURCENR) LIKE CONCAT('%',?,'%') ORDER BY RESOURCENR", nativeQuery = true)
	List<String> findByresourceNrLike(Integer resourceNr);

	@Query(value = "select max(RESOURCENR) from RE_RESOURCES with(nolock)", nativeQuery = true)
	Integer getMaxResourceNr();

	@Query(value = "select TOP 3 * from RE_RESOURCES with(nolock) order by CREATEDON desc,MODIFIEDON desc", nativeQuery = true)
	List<ReResource> getByresourceTop3();

	@Query(value = QueryConstants.RESGROUPCOUNTQUERY, nativeQuery = true)
	<T> List<T> getResGroupCount();

	@Query(value = QueryConstants.LANGCOUNTQUERY, nativeQuery = true)
	<T> List<T> getResLangCount();

}