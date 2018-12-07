package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReTurkishtr;

@Component
public interface ReTurkishtrRep extends JpaRepository<ReTurkishtr, Long> {

	@Query(value = "SELECT * FROM RE_TURKISHTR WHERE RESOURCEITEMREF = :resourceitemref", nativeQuery = true)
	ReTurkishtr getresourceitemrefEquals(@Param("resourceitemref") Integer resourceitemref);
}
