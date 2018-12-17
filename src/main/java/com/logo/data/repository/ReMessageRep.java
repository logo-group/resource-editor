package com.logo.data.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.logo.data.entity.ReMessage;

@Component
@Scope("prototype")
public interface ReMessageRep extends JpaRepository<ReMessage, Long> {

	List<ReMessage> findByConsIdLikeIgnoreCase(String consIdFilter);

	List<ReMessage> findByModuleLikeIgnoreCase(String moduleFilter);

}