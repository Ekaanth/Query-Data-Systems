package com.qds.sa.jparepository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.util.constant.ActiveStatus;

@Repository
public interface ForgotAccessRep extends JpaRepository<ForgotAccess, Long>{

	public ArrayList<ForgotAccess> findByUstatus(ActiveStatus status);
	
	public ForgotAccess findByUemailid(String emailid);
	
	@Query(value = "SELECT f FROM forgotaccess f  WHERE LOWER(f.uemailid) is LOWER(:uemailid) ANDLOWER(f.ustatus) = LOWER(:ustatus)", nativeQuery = true)
	public ForgotAccess findByUemailid_status(@Param("ustatus") String ustatus , @Param("uemailid") String uemailid);

	@Query(value= "SELECT * FROM forgotaccess  WHERE uemailid = :email AND ustatus = 'ACTIVE'" , nativeQuery=true)
	public ForgotAccess findByUemailidAndStatus(@Param("email")String uemailid);

}
