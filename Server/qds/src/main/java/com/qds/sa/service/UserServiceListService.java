package com.qds.sa.service;

import java.util.ArrayList;

import com.qds.sa.domain.UserServiceList;
import com.qds.sa.domain.tmodel.TaddDelService;

public interface UserServiceListService {

	public UserServiceList addNewService(UserServiceList userServiceList);
	
	public ArrayList<UserServiceList> findByUid(String uid);

	public UserServiceList addService(TaddDelService userDetails);

	public UserServiceList deleteService(TaddDelService userDetails);

	public ArrayList<UserServiceList> findByUidAndStatus(String uid);
	
}
