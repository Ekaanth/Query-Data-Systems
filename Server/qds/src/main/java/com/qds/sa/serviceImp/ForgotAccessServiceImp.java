package com.qds.sa.serviceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.tmodel.TOnboardExistingUser;
import com.qds.sa.jparepository.ForgotAccessRep;
import com.qds.sa.jparepository.UserProfileRep;
import com.qds.sa.service.ForgotAccessService;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.helper.EmailService;

@Service
@Transactional
public class ForgotAccessServiceImp implements ForgotAccessService {

	@Autowired
	ForgotAccessRep forgotAccessRep;
	
	@Autowired
	UserProfileRep userProfileRep;
	
	@Autowired
	EmailService emailservice;
	
	@Override
	public ForgotAccess forgotaccess(ForgotAccess userdetail) {
		if((forgotAccessRep.findByUemailidAndStatus(userdetail.getUemailid()) == null)) {
			if(userProfileRep.findByUemailid(userdetail.getUemailid()) != null){
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			userdetail.setTimestamp(timeStamp);
			userdetail.setUstatus(ActiveStatus.ACTIVE);
			forgotAccessRep.save(userdetail);
			emailservice.sendForgotEmail(userdetail.getUemailid());
			}
		}
		return userdetail;
	}

	@Override
	public ArrayList<ForgotAccess> findByUstatus(ActiveStatus status) {
		return forgotAccessRep.findByUstatus(status);
	}

	@Override
	public ForgotAccess findByUemailid_status(String emailId) {
		return forgotAccessRep.findByUemailid_status("ACTIVE", emailId);
	}

	@Override
	public ForgotAccess updateTheStatus(TOnboardExistingUser user) {
		ForgotAccess userUpdate = forgotAccessRep.findByUemailid(user.getUemailid());
		userUpdate.setUstatus(ActiveStatus.INACTIVE);
		return forgotAccessRep.save(userUpdate);
	}

	@Override
	public ForgotAccess findByUemailid(String emailId) {
		return forgotAccessRep.findByUemailid(emailId);
	}
}
