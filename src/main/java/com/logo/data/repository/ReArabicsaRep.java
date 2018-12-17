package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReArabicsa;

@Component
@Scope("prototype")
public interface ReArabicsaRep extends JpaRepository<ReArabicsa, Long> {

}
