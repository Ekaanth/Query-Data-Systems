package com.qds.sa.web.api;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qds.sa.domain.ContactUsDetails;
import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.service.ContactUsDetailsService;
import com.qds.sa.service.RequestAccessService;

@RestController
@RequestMapping("/qds/resources/api")
@CrossOrigin(origins= "http://localhost:4200/")
public class HomeRestController {

	@Autowired
	ContactUsDetailsService contactUsDetailsService;
	
	@Autowired
	RequestAccessService requestAccessService;

	
	
	@RequestMapping(
	        value = ("/contactus"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ContactUsDetails> requestAccess(@RequestBody ContactUsDetails details)
	{
		ContactUsDetails resp = contactUsDetailsService.saveDetails(details);
		return new ResponseEntity<ContactUsDetails>(resp , HttpStatus.OK);
	}
	
	@RequestMapping(
	        value = ("/getInactiveRequestAccess"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<RequestAccess>> getInactiveRequestAccess()
	{
		List<RequestAccess> resp = requestAccessService.getAllInactiveStatus();
		
		return new ResponseEntity<Collection<RequestAccess>>(resp,HttpStatus.OK);
	}
	
	
}
