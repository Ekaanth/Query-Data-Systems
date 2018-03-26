package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.Enquiry;

@Repository
public interface EnquiryRep extends JpaRepository<Enquiry, Long>{

}
