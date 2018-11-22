package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReRomanianroRep extends JpaRepository<ReRomanianro, Long>{

	List<ReRomanianro> findAllBy(Pageable pageable);

	List<ReRomanianro> findByresourceitemrefEquals(Integer resourceitemref);
}
