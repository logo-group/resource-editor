package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.RePersianir;

@Component
public interface RePersianirRep extends JpaRepository<RePersianir, Long> {

}
