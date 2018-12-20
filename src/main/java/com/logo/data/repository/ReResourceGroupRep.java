package com.logo.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.logo.data.entity.ReResourceGroup;

@Component
@Scope("prototype")
@Transactional(readOnly = true)
public interface ReResourceGroupRep extends JpaRepository<ReResourceGroup, String> {

}
