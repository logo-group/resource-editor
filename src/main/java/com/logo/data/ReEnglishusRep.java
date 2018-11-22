package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ReEnglishusRep extends JpaRepository<ReEnglishus, Long>{

	List<ReEnglishus> findAllBy(Pageable pageable);

	List<ReEnglishus> findByresourceitemrefEquals(Integer resourceitemref);
	
	@Query(value="SELECT * FROM re_englishus WHERE resourceitemref = ?1", nativeQuery = true)
	ReEnglishus getresourceitemrefEquals(Integer resourceitemref);

}
