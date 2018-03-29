package com.qds.sa.serviceImp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.UserProfile;
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
	public ForgotAccess forgotaccess(ForgotAccess fas) {
		Date date = new Date();
		UserProfile userprofile = userProfileRep.findByUemailidAndMobilenumber(fas.getUemailid() , fas.getUmobilenumber());
		fas.setUid(userprofile.getUid());
		fas.setSentdate(new Date(date.getTime()));
		fas.setUstatus(ActiveStatus.ACTIVE);
		ForgotAccess res = forgotAccessRep.save(fas);
		emailservice.sendForgotEmail(res);
		return res;
	}
}
