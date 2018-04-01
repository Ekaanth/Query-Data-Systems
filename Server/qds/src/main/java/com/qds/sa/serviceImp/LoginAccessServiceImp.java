package com.qds.sa.serviceImp;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.LoginAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.UserServiceList;
import com.qds.sa.domain.tmodel.TLoginUser;
import com.qds.sa.domain.tmodel.TUserService;
import com.qds.sa.jparepository.LoginAccessRep;
import com.qds.sa.service.LoginAccessService;
import com.qds.sa.service.UserProfileService;
import com.qds.sa.service.UserServiceListService;
import com.qds.sa.util.constant.LoginResult;

@Service
@Transactional
public class LoginAccessServiceImp implements LoginAccessService{

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	LoginAccessRep loginAccessRep;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserServiceListService userServiceListService;
	
	Date date = new Date();
	
	
	public TLoginUser addLoginDetails(LoginAccess details) {
		details.setTimestamp(new Date(date.getTime()));
		UserProfile userlogin = checkUserDetails(details);
		if(userlogin != null ){
			details.setUresult(LoginResult.SUCCESS);
			TLoginUser responseModel = new TLoginUser();
			responseModel.setUid(userlogin.getUid());
			responseModel.setUname(userlogin.getUname());
			responseModel.setUemailid(userlogin.getUemailid());
			responseModel.setUmobilenumber(userlogin.getUmobilenumber());
			ArrayList<UserServiceList> usersericelist = new ArrayList<UserServiceList>();
			ArrayList<UserServiceList> userServiceList = userServiceListService.findByUid(userlogin.getUid());
			loginAccessRep.save(details);
			return responseModel;
			
		}else {
			details.setUresult(LoginResult.FAILED);
			loginAccessRep.save(details);
			return null;
		}
		
	}


	private UserProfile checkUserDetails(LoginAccess details) {
			
		return userProfileService.findUserProfile(details.getUqueryid() , details.getUpassword());
	}
	

}
