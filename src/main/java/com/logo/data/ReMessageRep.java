package com.logo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReMessageRep extends JpaRepository<ReMessage, Long> {
    
    List<ReMessage> findAllBy(Pageable pageable);
    
    List<ReMessage> findByConsIdLikeIgnoreCase(String consIdFilter);
    
    List<ReMessage> findByModuleLikeIgnoreCase(String moduleFilter);
    
}