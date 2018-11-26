package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabiceg;

@Component
public interface ReArabicegRep extends JpaRepository<ReArabiceg, Long>{

	List<ReArabiceg> findAllBy(Pageable pageable);

	List<ReArabiceg> findByresourceitemrefEquals(Integer resourceitemref);
}
