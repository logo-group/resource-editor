package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReEnglishus;

@Component
public interface ReEnglishusRep extends JpaRepository<ReEnglishus, Long> {

	List<ReEnglishus> findAllBy(Pageable pageable);

	List<ReEnglishus> findByresourceitemrefEquals(Integer resourceitemref);

	@Query(value = "SELECT * FROM RE_ENGLISHUS WHERE RESOURCEITEMREF = :resourceitemref", nativeQuery = true)
	ReEnglishus getresourceitemrefEquals(Integer resourceitemref);

}
