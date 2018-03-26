package com.qds.sa.serviceImp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.jparepository.ForgotAccessRep;
import com.qds.sa.service.ForgotAccessService;

@Service
@Transactional
public class ForgotAccessServiceImp implements ForgotAccessService {

	@Autowired
	ForgotAccessRep forgotAccessRep;
	
	@Override
	public ForgotAccess forgotaccess(ForgotAccess fas) {
		Date date = new Date();
		fas.setSentdate(new Date(date.getTime()));
		ForgotAccess res = forgotAccessRep.save(fas);
		
		return res;
	}
}
