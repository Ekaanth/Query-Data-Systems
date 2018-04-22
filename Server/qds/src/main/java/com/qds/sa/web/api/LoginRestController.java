package com.qds.sa.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.LoginAccess;
import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.tmodel.TLoginUser;
import com.qds.sa.service.ForgotAccessService;
import com.qds.sa.service.LoginAccessService;
import com.qds.sa.service.RequestAccessService;
import com.qds.sa.service.UserProfileService;



@RestController
@RequestMapping("/qds/resources/api")
@CrossOrigin(origins= "http://localhost:4200/")
public class LoginRestController {


	@Autowired
	RequestAccessService requestAccessService;
	
	@Autowired
	ForgotAccessService forgotAccessService;
	
	@Autowired
	LoginAccessService loginAccessService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@CrossOrigin(origins= "http://localhost:4200/requestAccess")
	@RequestMapping(
	        value = ("/requestAccess"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<RequestAccess> requestAccess(@RequestBody RequestAccess requestAccess)
	{
		RequestAccess res = requestAccessService.registerUser(requestAccess);
		return new ResponseEntity<RequestAccess>(res , HttpStatus.OK);
	}
	
	
	@RequestMapping(
	        value = ("/forgotLogin"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins= "http://localhost:4200/forgot")
	public ResponseEntity<ForgotAccess> forgotLogin(@RequestBody ForgotAccess requestAccess)
	{
		 forgotAccessService.forgotaccess(requestAccess);
		 return new ResponseEntity<ForgotAccess>(HttpStatus.OK);
	}
	
	@RequestMapping(
	        value = ("/userlogin"),
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins= "http://localhost:4200/")
	public ResponseEntity<TLoginUser> userlogin(@RequestBody LoginAccess requestAcces)
	{
		TLoginUser res = loginAccessService.addLoginDetails(requestAcces);
		 return new ResponseEntity<TLoginUser>(res , HttpStatus.OK);
	}
	
	@RequestMapping(
	        value = ("/findByUqueryId/{id}"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins= "http://localhost:4200/")
	public ResponseEntity<UserProfile> userlogin(@PathVariable("id") String uqueryid)
	{
		UserProfile res = userProfileService.findUserProfileByUqueryid(uqueryid);
		 return new ResponseEntity<UserProfile>(HttpStatus.OK);
	}
	
}
