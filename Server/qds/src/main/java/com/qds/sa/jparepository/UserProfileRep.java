package com.qds.sa.jparepository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UserProfile;
import com.qds.sa.util.constant.ActiveStatus;

@Repository
public interface UserProfileRep extends JpaRepository<UserProfile, Long> {

	public UserProfile findByUqueryid(String id);
	
	public UserProfile findByUid(String uid);
	
	public ArrayList<UserProfile> findByUprofilestatus(ActiveStatus activeStatus);
	
	public UserProfile findByUemailid(String emailId);
	
	@Query(value= "SELECT * FROM forgotaccess  WHERE uemailid = :email AND ustatus = 'ACTIVE'" , nativeQuery=true)
	public UserProfile findByUemailidAndStatus(@Param("email")String emailid);

}
