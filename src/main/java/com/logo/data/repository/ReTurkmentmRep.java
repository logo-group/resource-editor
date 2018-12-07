package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReTurkmentm;

@Component
public interface ReTurkmentmRep extends JpaRepository<ReTurkmentm, Long> {

}
