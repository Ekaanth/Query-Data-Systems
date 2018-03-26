package com.qds.sa.serviceImp;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.RequestAccess;
import com.qds.sa.jparepository.RequestAccessRep;
import com.qds.sa.service.RequestAccessService;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.helper.EmailService;

@Service
@Transactional
public class RequestAccessServiceImp implements RequestAccessService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RequestAccessRep requestAccessRep;
	
	@Autowired EmailService emailService;
	
	@Override
	public RequestAccess registerUser(RequestAccess requestAccess) {
		Date date = new Date();
		RequestAccess resAdd = requestAccessRep.save(requestAccess);
		Long id = resAdd.getId();
		RequestAccess findUpdate = requestAccessRep.findOne(id);
		findUpdate.setUid("QDS-" + id);
		findUpdate.setUrequeststatus(ActiveStatus.INACTIVE);
		findUpdate.setSentdate(new Date(date.getTime()));
		if(resAdd != null) {
			emailService.sendRequestEmail(findUpdate);
		}
		return requestAccessRep.save(findUpdate);

	}

	@Override
	public List<RequestAccess> getAllInactiveStatus() {
		
		List<RequestAccess> inactive = requestAccessRep.findByUrequeststatus(ActiveStatus.INACTIVE);
		return inactive; 
	}
}
