package com.logo.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReUser;

@Component
public interface ReUserRep extends JpaRepository<ReUser, Long> {

	/* A version to fetch List instead of Page to avoid extra count query. */
	List<ReUser> findAllBy(Pageable pageable);

	List<ReUser> findByusernameLikeIgnoreCase(String nameFilter);

	ReUser findByid(Integer id);

	@Query(value = "select * from RE_USERS where ID = :id", nativeQuery = true)
	List<ReUser> findByidList(@Param("id") Integer id);

	// For lazy loading and filtering
	List<ReUser> findByusernameLikeIgnoreCase(String nameFilter, Pageable pageable);

	@Query(value = "select USERNAME from RE_USERS", nativeQuery = true)
	List<String> findAllByUserName();

	long countByusernameLike(String nameFilter);

}