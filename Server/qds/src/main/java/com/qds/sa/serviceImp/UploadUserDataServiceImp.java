package com.qds.sa.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qds.sa.domain.UploadUserData;
import com.qds.sa.jparepository.UploadUserDataRep;
import com.qds.sa.service.UploadUserDataService;

@Service
public class UploadUserDataServiceImp implements UploadUserDataService{

	
	@Autowired
	UploadUserDataRep uploadUserDataRep;
	
	@Override
	public UploadUserData saveUploadedFile(UploadUserData uploadUserData) {
		return uploadUserDataRep.save(uploadUserData);
	}
}
