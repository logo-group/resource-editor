package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReRussianru;

@Component
@Scope("prototype")
public interface ReRussianruRep extends JpaRepository<ReRussianru, Long> {

}
