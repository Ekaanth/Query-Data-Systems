package com.qds.sa.jparepository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UserServiceList;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Repository
public interface UserServiceListRep extends JpaRepository<UserServiceList, Long>{

	public ArrayList<UserServiceList> findByUid(String uid);
	
	@Query(value= "SELECT * FROM userservice user  WHERE user.uid = :uid AND user.uservicetype = :status " , nativeQuery=true)
	public UserServiceList findByUidAndServiceType(@Param("uid") String uid,  @Param("status") String datasupport);

	@Query(value= "SELECT * FROM userservice user  WHERE user.uid = :uid AND user.uservicestatus = 'ACTIVE'" , nativeQuery=true)
	public ArrayList<UserServiceList> findByUidAndStatus(@Param("uid") String uid);
}
 