package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReStandard;

@Component
public interface ReStandardRep extends JpaRepository<ReStandard, Long> {

}
