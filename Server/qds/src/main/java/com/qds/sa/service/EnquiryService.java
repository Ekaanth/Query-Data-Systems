package com.qds.sa.service;

import com.qds.sa.domain.Enquiry;
import com.qds.sa.domain.tmodel.TEnquiry;

public interface EnquiryService {
	
	public Enquiry addNewEnquiry(TEnquiry enquiry);
}
