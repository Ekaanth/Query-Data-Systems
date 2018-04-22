package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.AdminFileUpload;

@Repository
public interface AdminFileUploadRep extends JpaRepository<AdminFileUpload, Long> {

	@Query(value="Select * from adminupload a where a.uid = :uid Order by a.timestamp desc LIMIT 1" , nativeQuery=true)
	public AdminFileUpload findPathByUid(@Param("uid") String uid);

}
