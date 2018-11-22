package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReAzerbaijaniazRep extends JpaRepository<ReAzerbaijaniaz, Long>{

	List<ReAzerbaijaniaz> findAllBy(Pageable pageable);

	List<ReAzerbaijaniaz> findByresourceitemrefEquals(Integer resourceitemref);
}
