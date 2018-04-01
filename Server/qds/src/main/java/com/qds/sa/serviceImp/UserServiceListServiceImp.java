package com.qds.sa.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.UserServiceList;
import com.qds.sa.jparepository.UserServiceListRep;
import com.qds.sa.service.UserServiceListService;

@Service
@Transactional
public class UserServiceListServiceImp implements UserServiceListService{

	
	@Autowired
	UserServiceListRep userServiceListRep;
	@Override
	public UserServiceList addNewService(UserServiceList userServiceList) {
		return userServiceListRep.save(userServiceList);
	}
	
	
	@Override
	public ArrayList<UserServiceList> findByUid(String uid) {
		return userServiceListRep.findByUid(uid);
	}
}
