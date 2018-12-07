package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReTurkishtr;

@Component
public interface ReTurkishtrRep extends JpaRepository<ReTurkishtr, Long> {

	List<ReTurkishtr> findAllBy(Pageable pageable);

	List<ReTurkishtr> findByresourceitemrefEquals(Integer resourceitemref);

	@Query(value = "SELECT * FROM RE_TURKISHTR WHERE RESOURCEITEMREF = :resourceitemref", nativeQuery = true)
	List<ReTurkishtr> findByresourceitemrefEqualsForDelete(@Param("resourceitemref") Integer resourceitemref);

	@Query(value = "SELECT * FROM RE_TURKISHTR WHERE RESOURCEITEMREF = :resourceitemref", nativeQuery = true)
	ReTurkishtr getresourceitemrefEquals(@Param("resourceitemref") Integer resourceitemref);
}
