package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReTurkmentmRep extends JpaRepository<ReTurkmentm, Long>{

	List<ReTurkmentm> findAllBy(Pageable pageable);

	List<ReTurkmentm> findByresourceitemrefEquals(Integer resourceitemref);
	
}
