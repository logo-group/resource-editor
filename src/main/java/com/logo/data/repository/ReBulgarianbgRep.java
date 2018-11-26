package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReBulgarianbg;

@Component
public interface ReBulgarianbgRep extends JpaRepository<ReBulgarianbg, Long>{

	List<ReBulgarianbg> findAllBy(Pageable pageable);

	List<ReBulgarianbg> findByresourceitemrefEquals(Integer resourceitemref);
}
