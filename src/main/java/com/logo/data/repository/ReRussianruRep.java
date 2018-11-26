package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReRussianru;

@Component
public interface ReRussianruRep extends JpaRepository<ReRussianru, Long>{

	List<ReRussianru> findAllBy(Pageable pageable);

	List<ReRussianru> findByresourceitemrefEquals(Integer resourceitemref);
}
