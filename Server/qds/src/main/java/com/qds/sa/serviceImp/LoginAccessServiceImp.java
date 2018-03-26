package com.qds.sa.serviceImp;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.LoginAccess;
import com.qds.sa.jparepository.LoginAccessRep;
import com.qds.sa.service.LoginAccessService;
import com.qds.sa.service.UserProfileService;
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
	
	Date date = new Date();
	
	
	public LoginAccess addLoginDetails(LoginAccess details) {
		details.setTimestamp(new Date(date.getTime()));
		if(checkUserDetails(details)){
			details.setUresult(LoginResult.SUCCESS);	
			
		}else {
			details.setUresult(LoginResult.FAILED);
		}
		return loginAccessRep.save(details);
	}


	private boolean checkUserDetails(LoginAccess details) {
			
		return userProfileService.findUserProfile(details.getUqueryid() , details.getUpassword());
	}
	

}
