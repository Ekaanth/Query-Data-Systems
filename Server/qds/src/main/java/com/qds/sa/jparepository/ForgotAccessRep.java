package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.ForgotAccess;

@Repository
public interface ForgotAccessRep extends JpaRepository<ForgotAccess, Long> {

}
