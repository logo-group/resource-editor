package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReGeorgiange;

@Component
public interface ReGeorgiangeRep extends JpaRepository<ReGeorgiange, Long> {

}
