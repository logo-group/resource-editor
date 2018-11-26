package com.logo.data.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.Project;

@Component
public interface ProjectRep extends JpaRepository<Project, Long> {
    
    /* A version to fetch List instead of Page to avoid extra count query. */
    List<Project> findAllBy(Pageable pageable);
    
    List<Project> findByprojectNameLikeIgnoreCase(String nameFilter);
    
    // For lazy loading and filtering
    List<Project> findByprojectNameLikeIgnoreCase(String nameFilter, Pageable pageable);
    
    long countByprojectNameLike(String nameFilter);
}