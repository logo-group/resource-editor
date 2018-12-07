package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReAzerbaijaniaz;

@Component
public interface ReAzerbaijaniazRep extends JpaRepository<ReAzerbaijaniaz, Long> {

}
