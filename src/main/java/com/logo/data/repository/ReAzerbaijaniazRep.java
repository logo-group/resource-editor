package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReAzerbaijaniaz;

@Component
public interface ReAzerbaijaniazRep extends JpaRepository<ReAzerbaijaniaz, Long>{

	List<ReAzerbaijaniaz> findAllBy(Pageable pageable);

	List<ReAzerbaijaniaz> findByresourceitemrefEquals(Integer resourceitemref);
}
