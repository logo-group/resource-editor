package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabicsa;

@Component
public interface ReArabicsaRep extends JpaRepository<ReArabicsa, Long>{

	List<ReArabicsa> findAllBy(Pageable pageable);

	List<ReArabicsa> findByresourceitemrefEquals(Integer resourceitemref);
}
