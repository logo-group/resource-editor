package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabiceg;

@Component
public interface ReArabicegRep extends JpaRepository<ReArabiceg, Long> {

}
