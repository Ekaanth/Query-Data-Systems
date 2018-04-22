package com.qds.sa.web.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qds.sa.domain.AdminFileUpload;
import com.qds.sa.domain.ForgotAccess;
import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.UserServiceList;
import com.qds.sa.domain.tmodel.TOnboardExistingUser;
import com.qds.sa.domain.tmodel.TaddDelService;
import com.qds.sa.service.AdminFileUploadService;
import com.qds.sa.service.ForgotAccessService;
import com.qds.sa.service.RequestAccessService;
import com.qds.sa.service.UserProfileService;
import com.qds.sa.service.UserServiceListService;
import com.qds.sa.util.constant.ActiveStatus;

@RestController
@RequestMapping("/qds/resources/api/admin")
@CrossOrigin(origins= "http://localhost:4200/")
public class AdminRestController {
	
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	RequestAccessService requestAccessService;
	
	@Autowired
	ForgotAccessService forgotAccessService;
	
	@Autowired
	UserServiceListService userServiceListService;
	
	@Autowired
	AdminFileUploadService adminFileUploadService;
	
	private static String UPLOADED_FOLDER ="C://Apache24//htdocs//downloadUpload//";
	
	@RequestMapping(
	        value = ("/getInactiveRequestAccess"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<RequestAccess>> getInactiveRequestAccess()
	{
		List<RequestAccess> resp = requestAccessService.getAllInactiveStatus();
		return new ResponseEntity<Collection<RequestAccess>>(resp,HttpStatus.OK);
	}
	
	@RequestMapping(
	        value = ("/findAllForgotUser"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ArrayList<ForgotAccess>> findAllForgotUser()throws Exception
	{
		try {
			ArrayList<ForgotAccess> resp = forgotAccessService.findByUstatus(ActiveStatus.ACTIVE);
			return new ResponseEntity<ArrayList<ForgotAccess>>(resp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
	
	@RequestMapping(
	        value = ("/onboardExistingUser"),
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<UserProfile> onboardExistingUser(@RequestBody TOnboardExistingUser userDetails)throws Exception
	{
		try {
			UserProfile resp = userProfileService.addExistingUser(userDetails);
			ForgotAccess forgot = forgotAccessService.updateTheStatus(userDetails);
			return new ResponseEntity<UserProfile>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(
	        value = ("/getAllActiveUser"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ArrayList<String>> getAllActiveUser()throws Exception
	{
		try {
			ArrayList<String> resp = userProfileService.getAllActiveUser();
			return new ResponseEntity<ArrayList<String>>(resp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(
	        value = ("/addDeleteservice"),
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<String> addDeleteservice(@RequestBody TaddDelService userDetails)throws Exception
	{
		try {
			if(userDetails.getUserviceRequest().equalsIgnoreCase("addService")) {
				userServiceListService.addService(userDetails);
			}else {
				userServiceListService.deleteService(userDetails);
			}
			
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(
	        value = ("/uploadAdminDocuments"),
	        method = RequestMethod.POST,
	        headers = "content-type=multipart/form-data")
	public String uploadAdminDocuments(@RequestParam("file") MultipartFile files, @RequestParam("uid") String uid)throws Exception
	{
		new File(UPLOADED_FOLDER + "//" + uid).mkdirs();
			 try {
				 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				 byte[] bytes = files.getBytes();
		            Path path = Paths.get(UPLOADED_FOLDER + "//" + uid + "//"+ timeStamp+ ".zip");
		            Files.write(path, bytes);
		            Date date = new Date();
		            AdminFileUpload admin = new AdminFileUpload();
		            admin.setUid(uid);
		            admin.setFilename(path.toString());
		            admin.setTimestamp(timeStamp);
		            adminFileUploadService.addnewFile(admin); 
             } catch (IOException e) {
                 e.printStackTrace();
             }
			 return "YES";
		}
	
	@RequestMapping(
	        value = ("/uniqueUqueryId/quserid/{quserid}"),
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<String> uniqueUqueryId(@PathVariable("quserid") String uQueryId)
	{
		String resp = userProfileService.uniqueUqueryId(uQueryId);
		return new ResponseEntity<String>(resp , HttpStatus.OK);
	}
	}

