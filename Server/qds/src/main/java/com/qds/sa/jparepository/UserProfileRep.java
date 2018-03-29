package com.qds.sa.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UserProfile;

@Repository
public interface UserProfileRep extends JpaRepository<UserProfile, Long> {

	public UserProfile findByUqueryid(String id);
	
	public UserProfile findByUemailid(String emailId);
	
	@Query(value = "SELECT s FROM userprofile s WHERE LOWER(s.uemailid) like LOWER(:uemailid) and LOWER(s.umobilenumber) = LOWER(:umobilenumber)", nativeQuery = true)
	public UserProfile findByUemailidAndMobilenumber(@Param("uemailid")String emailid, @Param("umobilenumber")String mobileno);
}
