package com.qds.sa.service;

import java.util.ArrayList;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.tmodel.TOnboardExistingUser;
import com.qds.sa.util.constant.ActiveStatus;

public interface ForgotAccessService {

	public ForgotAccess forgotaccess(ForgotAccess details);
	
	public ArrayList<ForgotAccess> findByUstatus(ActiveStatus status);
	
	public ForgotAccess findByUemailid_status(String emailId);
	
	public ForgotAccess updateTheStatus(TOnboardExistingUser user);
	
	public ForgotAccess findByUemailid(String emailId);
}
