package com.qds.sa.serviceImp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.UserServiceList;
import com.qds.sa.service.UserServiceListService;

@Service
@Transactional
public class UserServiceListServiceImp implements UserServiceListService{

	@Override
	public UserServiceList addNewService(UserServiceList userServiceList) {
		// TODO Auto-generated method stub
		return null;
	}
}
