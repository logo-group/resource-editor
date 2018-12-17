package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.RePersianir;

@Component
@Scope("prototype")
public interface RePersianirRep extends JpaRepository<RePersianir, Long> {

}
