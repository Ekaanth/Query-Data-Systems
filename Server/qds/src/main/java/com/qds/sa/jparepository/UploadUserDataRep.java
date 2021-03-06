package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UploadUserData;

@Repository
public interface UploadUserDataRep extends JpaRepository<UploadUserData, Long> {

}
