package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReEnglishus;

@Component
@Scope("prototype")
public interface ReEnglishusRep extends JpaRepository<ReEnglishus, Long> {

	@Query(value = "SELECT * FROM RE_ENGLISHUS WHERE RESOURCEITEMREF = :resourceitemref", nativeQuery = true)
	ReEnglishus getresourceitemrefEquals(@Param("resourceitemref") Integer resourceitemref);

}
