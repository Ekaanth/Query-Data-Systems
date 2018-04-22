package com.qds.sa.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.service.ForgotAccessService;
import com.qds.sa.service.UserProfileService;

@RestController
@RequestMapping("/qds/resources/api/user")
@CrossOrigin(origins= "http://localhost:4200/")
public class UserRestController {

	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	ForgotAccessService forgotAccessService;
	
	@RequestMapping(
	        value = ("/adduser"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserProfile> addUser(@RequestBody UserProfile user)throws Exception
	{
		try {
			boolean resp = userProfileService.addUser(user);
			return new ResponseEntity<UserProfile>( HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	
	@RequestMapping(
	        value = ("/findForgotUserByEmailId"),
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ForgotAccess> findForgotUserByEmailId(@RequestBody String emailId)throws Exception
	{
		try {
			ForgotAccess resp = forgotAccessService.findByUemailid(emailId);
			return new ResponseEntity<ForgotAccess>(resp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
	
	@RequestMapping(
	        value = ("/addForgotUser"),
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ForgotAccess> addForgotUser(@RequestBody String emailId)throws Exception
	{
		try {
			ForgotAccess resp = forgotAccessService.findByUemailid_status(emailId);
			return new ResponseEntity<ForgotAccess>(resp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	

}
