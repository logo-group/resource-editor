package com.logo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.Project;

@Component
public interface ProjectRep extends JpaRepository<Project, Long> {

	List<Project> findByprojectNameLikeIgnoreCase(String nameFilter);

}