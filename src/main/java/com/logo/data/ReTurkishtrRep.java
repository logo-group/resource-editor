package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ReTurkishtrRep extends JpaRepository<ReTurkishtr, Long>{

	List<ReTurkishtr> findAllBy(Pageable pageable);

	List<ReTurkishtr> findByresourceitemrefEquals(Integer resourceitemref);
	
	@Query(value="SELECT * FROM re_turkishtr WHERE resourceitemref = ?1", nativeQuery = true)
	List<ReTurkishtr> findByresourceitemrefEqualsForDelete(Integer resourceitemref);
	
	@Query(value="SELECT * FROM re_turkishtr WHERE resourceitemref = ?1", nativeQuery = true)
	ReTurkishtr getresourceitemrefEquals(Integer resourceitemref);
}
