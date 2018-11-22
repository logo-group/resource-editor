package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReArabicegRep extends JpaRepository<ReArabiceg, Long>{

	List<ReArabiceg> findAllBy(Pageable pageable);

	List<ReArabiceg> findByresourceitemrefEquals(Integer resourceitemref);
}
