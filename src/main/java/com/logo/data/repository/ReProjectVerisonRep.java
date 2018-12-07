package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReProjectVersion;

@Component
public interface ReProjectVerisonRep extends JpaRepository<ReProjectVersion, Long> {

}