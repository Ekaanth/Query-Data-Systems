package com.qds.sa.serviceImp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qds.sa.domain.Enquiry;
import com.qds.sa.domain.tmodel.TEnquiry;
import com.qds.sa.jparepository.EnquiryRep;
import com.qds.sa.service.EnquiryService;
import com.qds.sa.util.constant.ServiceStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Service
@Transactional
public class EnquiryServiceImp implements EnquiryService {

	@Autowired
	EnquiryRep enquiryRep;
	
	
	@Override
	public Enquiry addNewEnquiry(TEnquiry enquiry) {
		Enquiry saveenq = new Enquiry();
		saveenq.setUfeedback(enquiry.getUfeedback());
		saveenq.setUipaddress(enquiry.getUipaddress());
		saveenq.setUipcountry(enquiry.getUipcountry());
		saveenq.setUquery(enquiry.getUquery());
		saveenq.setUid(enquiry.getUid());
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		saveenq.setTimestamp(timeStamp);
		
		if(enquiry.getServicetype() == "dataSupport") {
			saveenq.setServicetype(UserServiceConstant.DATASUPPORT);
		}else if(enquiry.getServicetype() == "mis") {
			saveenq.setServicetype(UserServiceConstant.MIS);
		}else if(enquiry.getServicetype() == "analytics") {
			saveenq.setServicetype(UserServiceConstant.ANALYTICS);
		}else {
			saveenq.setServicetype(UserServiceConstant.MR);
		}
		
		if(enquiry.getServicetype() == "addservice") {
			saveenq.setServicestatus(ServiceStatus.ADD);
		}else {
			saveenq.setServicestatus(ServiceStatus.DELETE);
		}
		return enquiryRep.save(saveenq);
	}


}
