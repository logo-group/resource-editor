package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabicjo;

@Component
public interface ReArabicjoRep extends JpaRepository<ReArabicjo, Long>{

	List<ReArabicjo> findAllBy(Pageable pageable);

	List<ReArabicjo> findByresourceitemrefEquals(Integer resourceitemref);
}
