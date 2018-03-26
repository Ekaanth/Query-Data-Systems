package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.ContactUsDetails;

@Repository
public interface ContactUsDetailsRep extends JpaRepository<ContactUsDetails, Long>{

}
