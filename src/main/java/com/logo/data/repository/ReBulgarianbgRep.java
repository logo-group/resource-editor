package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReBulgarianbg;

@Component
public interface ReBulgarianbgRep extends JpaRepository<ReBulgarianbg, Long> {

}
