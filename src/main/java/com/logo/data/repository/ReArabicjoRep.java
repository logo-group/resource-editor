package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabicjo;

@Component
public interface ReArabicjoRep extends JpaRepository<ReArabicjo, Long> {

}
