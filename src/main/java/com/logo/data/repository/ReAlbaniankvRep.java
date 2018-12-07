package com.logo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReAlbaniankv;

@Component
public interface ReAlbaniankvRep extends JpaRepository<ReAlbaniankv, Long> {

}
