package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReHelpDocsRep extends JpaRepository<ReHelpDocs, Long> {
    
    List<ReHelpDocs> findAllBy(Pageable pageable);

    List<ReHelpDocs> findByDocnameLikeIgnoreCase(String docnameFilter);
    
    List<ReHelpDocs> findByDoctitleLikeIgnoreCase(String doctitleFilter);
}