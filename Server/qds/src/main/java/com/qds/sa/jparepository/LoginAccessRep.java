package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.LoginAccess;


@Repository
public interface LoginAccessRep extends JpaRepository<LoginAccess, Long>{

}
