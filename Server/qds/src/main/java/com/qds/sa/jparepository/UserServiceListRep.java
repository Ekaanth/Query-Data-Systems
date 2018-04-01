package com.qds.sa.jparepository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qds.sa.domain.UserServiceList;

@Repository
public interface UserServiceListRep extends JpaRepository<UserServiceList, Long>{

	public ArrayList<UserServiceList> findByUid(String uid);
}
