package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReFrenchfr;

@Component
public interface ReFrenchfrRep extends JpaRepository<ReFrenchfr, Long> {

}
