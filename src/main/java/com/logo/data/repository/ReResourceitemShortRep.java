package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.logo.data.entity.ReResourceitemShort;
import com.logo.util.QueryConstants;
import com.logo.util.search.SearchParam;

@Component
@Scope("prototype")
@Transactional(readOnly = true)
public interface ReResourceitemShortRep extends JpaRepository<ReResourceitemShort, Long> {

	@Query(value = QueryConstants.SEARCHBYPARAMALL, countQuery = QueryConstants.SEARCHBYPARAMALLCOUNT, nativeQuery = true)
	Page<ReResourceitemShort> searchByParamAll(Pageable pageable, @Param("searchParam") SearchParam searchParam);

}