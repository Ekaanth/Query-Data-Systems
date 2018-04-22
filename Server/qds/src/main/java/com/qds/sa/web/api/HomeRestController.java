package com.qds.sa.web.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qds.sa.domain.AdminFileUpload;
import com.qds.sa.domain.ContactUsDetails;
import com.qds.sa.domain.Enquiry;
import com.qds.sa.domain.UploadUserData;
import com.qds.sa.domain.tmodel.TEnquiry;
import com.qds.sa.domain.tmodel.TuploadFile;
import com.qds.sa.jparepository.UploadUserDataRep;
import com.qds.sa.service.AdminFileUploadService;
import com.qds.sa.service.ContactUsDetailsService;
import com.qds.sa.service.EnquiryService;
import com.qds.sa.service.UserProfileService;
import com.qds.sa.util.constant.DataType;
import com.qds.sa.util.constant.UserServiceConstant;

@RestController
@RequestMapping("/qds/resources/api/home")
@CrossOrigin(origins= "http://localhost:4200/")
public class HomeRestController {

	@Autowired
	ContactUsDetailsService contactUsDetailsService;
	
	@Autowired
	EnquiryService enquiryService;
	
	@Autowired
	AdminFileUploadService adminFileUploadService;
	
	@Autowired
	UploadUserDataRep uploadUserDataRep;
	
	@Autowired
	UserProfileService userProfileService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private static String UPLOADED_FOLDER ="C://Apache24//htdocs//upload//";
	
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
	        value = ("/userEnquiry"),
	        method = RequestMethod.POST,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Enquiry> userEnquiry(@RequestBody TEnquiry details)
	{
		Enquiry resp = enquiryService.addNewEnquiry(details);
		return new ResponseEntity<Enquiry>(resp , HttpStatus.OK);
	}	
	
	@RequestMapping(
	        value = ("/uploadFile"),
	       headers = "content-type=multipart/form-data",
	        method = RequestMethod.POST)
	public@ResponseBody String  uploadFile(@RequestParam("file") MultipartFile files, @RequestParam("uid") String uid, @RequestParam("dataType") String dataType ,@RequestParam("serviceType") String serviceType )
	{
		new File(UPLOADED_FOLDER + "//" + uid).mkdirs();
		 try {
			 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			 byte[] bytes = files.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + "//" + uid +"//"+ uid+"_"+serviceType+"_"+dataType+"_"+timeStamp+".csv");
	            Files.write(path, bytes);
	            UploadUserData fileUpload = new UploadUserData();
	            fileUpload.setUid(uid);
	            if(dataType.equalsIgnoreCase("SALES")) {
	            	 fileUpload.setUdataType(DataType.SALES);
	            }else if(dataType.equalsIgnoreCase("PURCHASE")) {
	            	 fileUpload.setUdataType(DataType.PURCHASE);
	            }else if(dataType.equalsIgnoreCase("INVENTRY")) {
	            	 fileUpload.setUdataType(DataType.INVENTRY);
	            }else {
	            	
	            }
	            
	            if(serviceType.equalsIgnoreCase("dataSupport")) {
	            	 fileUpload.setUservice(UserServiceConstant.DATASUPPORT);
	            }else if(serviceType.equalsIgnoreCase("mis")) {
	            	 fileUpload.setUservice(UserServiceConstant.MIS);
	            }else if(serviceType.equalsIgnoreCase("analytics")) {
	            	 fileUpload.setUservice(UserServiceConstant.MR);
	            }else if(serviceType.equalsIgnoreCase("analytics")) {
	            	 fileUpload.setUservice(UserServiceConstant.ANALYTICS);
	            }else {
	            	
	            }
	            fileUpload.setUfilename(path.toString());
	           fileUpload.setTimestamp(timeStamp);
	           uploadUserDataRep.save(fileUpload);
	           
	           userProfileService.updateLastUploaded(uid);
	           
        } catch (IOException e) {
            e.printStackTrace();
        }
		 return "YES";
	}
	
	@RequestMapping(path = "/download/{uid}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(@PathVariable("uid") String uid) throws IOException {
		AdminFileUpload uIdFilepath = adminFileUploadService.findPathByUid(uid);
		System.out.println(uIdFilepath.getFilename());
		File file = new File(uIdFilepath.getFilename());
	    Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
	    return  ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(resource);
	}
}
