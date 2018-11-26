package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReGeorgiange;

@Component
public interface ReGeorgiangeRep extends JpaRepository<ReGeorgiange, Long>{

	List<ReGeorgiange> findAllBy(Pageable pageable);

	List<ReGeorgiange> findByresourceitemrefEquals(Integer resourceitemref);
}
