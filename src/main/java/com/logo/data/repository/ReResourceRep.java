package com.logo.data.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.logo.data.entity.ReResource;
import com.logo.util.QueryConstants;

@Component
@Scope("prototype")
@Transactional(readOnly = true)
public interface ReResourceRep extends JpaRepository<ReResource, Long> {

	@Query(value = "select * from RE_RESOURCES with(nolock) where ID = :ref", nativeQuery = true)
	ReResource findByid(@Param("ref") Integer ref);

	@Query(value = "select TOP 1 * from RE_RESOURCES with(nolock) where RESOURCENR = :resourceNr AND RESOURCEGROUP = :resourcegroup", nativeQuery = true)
	ReResource findByresourceNr(@Param("resourceNr") Integer resourceNr, @Param("resourcegroup") String resourcegroup);

	@Query(value = "select TOP 10 LTRIM(CONCAT(RESOURCEGROUP,'->',RESOURCENR)) from RE_RESOURCES with(nolock) where STR(RESOURCENR) LIKE CONCAT('%',:resourceNr,'%') ORDER BY RESOURCENR", nativeQuery = true)
	List<String> findByresourceNrLike(@Param("resourceNr") Integer resourceNr);

	@Query(value = "select max(RESOURCENR) from RE_RESOURCES with(nolock)", nativeQuery = true)
	Integer getMaxResourceNr();

	@Query(value = QueryConstants.RESGROUPCOUNTQUERY, nativeQuery = true)
	<T> List<T> getResGroupCount();

	@Query(value = QueryConstants.LANGCOUNTQUERY, nativeQuery = true)
	<T> List<T> getResLangCount();

}