package com.logo.data.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReHelpDocs;

@Component
@Scope("prototype")
public interface ReHelpDocsRep extends JpaRepository<ReHelpDocs, Long> {

	List<ReHelpDocs> findByDocnameLikeIgnoreCase(String docnameFilter);

	List<ReHelpDocs> findByDoctitleLikeIgnoreCase(String doctitleFilter);
}