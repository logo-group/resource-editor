package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReStandard;

@Component
public interface ReStandardRep extends JpaRepository<ReStandard, Long> {

	List<ReStandard> findAllBy(Pageable pageable);

	List<ReStandard> findByresourceitemrefEquals(Integer resourceitemref);
}
