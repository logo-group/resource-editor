package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReAlbaniankv;

@Component
public interface ReAlbaniankvRep extends JpaRepository<ReAlbaniankv, Long>{

	List<ReAlbaniankv> findAllBy(Pageable pageable);

	List<ReAlbaniankv> findByresourceitemrefEquals(Integer resourceitemref);
}
