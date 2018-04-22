package com.qds.sa.serviceImp;


import java.text.SimpleDateFormat;
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
import com.qds.sa.util.constant.ActiveStatus;
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
	
	public TLoginUser addLoginDetails(LoginAccess details) {
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		details.setTimestamp(timeStamp);
		TLoginUser responseModel = new TLoginUser();
		Boolean flag;
		UserProfile userdetails = userProfileService.findUserProfileByUqueryid(details.getUqueryid());
		if( userdetails != null) {
			if(userdetails.getUpassword().equals(details.getUpassword()) && userdetails.getUqueryid().equalsIgnoreCase(details.getUqueryid()) && userdetails.getUprofilestatus().equals(ActiveStatus.ACTIVE)) {
				details.setUresult(LoginResult.SUCCESS);
				loginAccessRep.save(details);
				responseModel.setUid(userdetails.getUid());
				responseModel.setUname(userdetails.getUname());
				responseModel.setUemailid(userdetails.getUemailid());
				responseModel.setUmobilenumber(userdetails.getUmobilenumber());
				responseModel.setUlastlogin(timeStamp);
				responseModel.setUlastuploded(userdetails.getUlastfileuplode());
				responseModel.setUlastpayment(userdetails.getUlastpayment());
				UserProfile userprofile = userProfileService.findUserProfileByUqueryid(userdetails.getUqueryid());
				userprofile.setUserlastlogin(timeStamp);
				userProfileService.saveUser(userprofile);
				ArrayList<UserServiceList> userServiceList = userServiceListService.findByUid(userdetails.getUid());
				responseModel.setUservice(userServiceList);
				flag= true;
			}else {
				flag = false;
			}
		}else {
			flag = false;
		}
		
		if(flag == true) {
			return responseModel;
		}else {
			details.setUresult(LoginResult.FAILED);
			loginAccessRep.save(details);
			return null;
		}
	}
}
	