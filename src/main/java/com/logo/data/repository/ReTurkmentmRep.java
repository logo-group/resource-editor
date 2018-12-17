package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReTurkmentm;

@Component
@Scope("prototype")
public interface ReTurkmentmRep extends JpaRepository<ReTurkmentm, Long> {

}
