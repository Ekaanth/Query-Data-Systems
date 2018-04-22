package com.qds.sa.serviceImp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.ContactUsDetails;
import com.qds.sa.jparepository.ContactUsDetailsRep;
import com.qds.sa.service.ContactUsDetailsService;

@Service
@Transactional
public class ContactUsDetailsServiceImp implements ContactUsDetailsService{

	@Autowired
	ContactUsDetailsRep contactUsDetailsRep;
	
	
	@Override
	public ContactUsDetails saveDetails(ContactUsDetails det) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		det.setTimestamp(timeStamp);
		return contactUsDetailsRep.save(det);
	}
}
