package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReGermande;

@Component
public interface ReGermandeRep extends JpaRepository<ReGermande, Long>{

	List<ReGermande> findAllBy(Pageable pageable);

	List<ReGermande> findByresourceitemrefEquals(Integer resourceitemref);
}
