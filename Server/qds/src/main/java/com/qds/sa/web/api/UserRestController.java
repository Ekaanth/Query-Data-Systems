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

import com.qds.sa.domain.UserProfile;
import com.qds.sa.service.UserProfileService;

@RestController
@RequestMapping("/qds/resources/api/user")
@CrossOrigin(origins= "http://localhost:4200/")
public class UserRestController {

	
	@Autowired
	UserProfileService userProfileService;
	
	@RequestMapping(
	        value = ("/adduser"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserProfile> addUser(@RequestBody UserProfile user)
	{
		UserProfile resp = userProfileService.addUser(user);
		return new ResponseEntity<UserProfile>(resp , HttpStatus.OK);
	}
	
}
