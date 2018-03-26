package com.qds.sa.serviceImp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.jparepository.RequestAccessRep;
import com.qds.sa.jparepository.UserProfileRep;
import com.qds.sa.service.UserProfileService;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.helper.EmailService;

@Service
public class UserprofileServiceImp implements UserProfileService{

	@Autowired
	UserProfileRep userprofilerepo;
	
	@Autowired
	RequestAccessRep requestaccessrepo;
	
	@Autowired 
	EmailService emailService;
	
	Date date = new Date();
	
	@Override
	public UserProfile addUser(UserProfile user) {
		
		String uId = user.getUid();
		RequestAccess returndata = updateRequestAccess(uId);
		user.setUname(returndata.getUname());
		user.setUemailid(returndata.getUemailid());
		user.setUmobilenumber(returndata.getUmobilenumber());
		user.setUstarteddate(new Date(date.getTime()));
		UserProfile saveuser = userprofilerepo.save(user);
		emailService.userAddesEmail(saveuser);
		return saveuser;
	}
	
	private RequestAccess updateRequestAccess(String uId) {
		RequestAccess req = requestaccessrepo.findByUid(uId);
		req.setUrequeststatus(ActiveStatus.ACTIVE);
		return requestaccessrepo.save(req);
	}

	@Override
	public Boolean findUserProfile(String queryid , String password) {
		Boolean result;
		UserProfile res = userprofilerepo.findByUqueryid(queryid);
		if(res != null) {
			if( password.equals(res.getUpassword())) {
				result = true;
			}else {
				result = false;
			}
		}else {
			result = false;
		}
		return result;
	}

	@Override
	public UserProfile findUserProfileByUqueryid(String queryId) {
		UserProfile userprofile = userprofilerepo.findByUqueryid(queryId);
		return userprofile;
	}

	@Override
	public UserProfile updateLogintime(String uqueryid) {
		UserProfile res = findUserProfileByUqueryid(uqueryid);
		res.setUserlastlogin(new Date(date.getTime()));
		userprofilerepo.save(res);
		res.setUpassword(null);
		return res;
	}
}
