package com.qds.sa.serviceImp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qds.sa.domain.AdminFileUpload;
import com.qds.sa.jparepository.AdminFileUploadRep;
import com.qds.sa.service.AdminFileUploadService;

@Service
@Transactional
public class AdminFileUploadServiceImp implements AdminFileUploadService{

	
	@Autowired
	AdminFileUploadRep adminFileUploadRep;
	
	@Override
	public AdminFileUpload addnewFile(AdminFileUpload admin) {
		return adminFileUploadRep.save(admin);
	}

	@Override
	public AdminFileUpload findPathByUid(String uid) {
		// TODO Auto-generated method stub
		return adminFileUploadRep.findPathByUid(uid);
	}
}
