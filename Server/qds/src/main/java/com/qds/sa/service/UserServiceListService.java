package com.qds.sa.service;

import java.util.ArrayList;

import com.qds.sa.domain.UserServiceList;

public interface UserServiceListService {

	public UserServiceList addNewService(UserServiceList userServiceList);
	
	public ArrayList<UserServiceList> findByUid(String uid);
}
