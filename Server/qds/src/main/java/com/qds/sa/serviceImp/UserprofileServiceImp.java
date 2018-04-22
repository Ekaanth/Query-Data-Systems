package com.qds.sa.serviceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qds.sa.domain.RequestAccess;
import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.UserServiceList;
import com.qds.sa.domain.tmodel.TOnboardExistingUser;
import com.qds.sa.jparepository.RequestAccessRep;
import com.qds.sa.jparepository.UserProfileRep;
import com.qds.sa.service.UserProfileService;
import com.qds.sa.service.UserServiceListService;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.UserServiceConstant;
import com.qds.sa.util.helper.EmailService;

@Service
@Transactional
public class UserprofileServiceImp implements UserProfileService{

	@Autowired
	UserProfileRep userprofilerepo;
	
	@Autowired
	RequestAccessRep requestaccessrepo;
	
	
	@Autowired
	UserServiceListService userServiceListService;
	
	
	@Autowired 
	EmailService emailService;
	
	@Override
	public boolean addUser(UserProfile user) {
		try {
		String uId = user.getUid();
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RequestAccess returndata = updateRequestAccess(uId);
		user.setUname(returndata.getUname());
		user.setUemailid(returndata.getUemailid());
		user.setUmobilenumber(returndata.getUmobilenumber());
		user.setUstarteddate(timeStamp);
		user.setUprofilestatus(ActiveStatus.ACTIVE);
		UserProfile saveuser = userprofilerepo.save(user);
		 String[] myData = saveuser.getUservice().replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
		for (String s: myData) {
			UserServiceList useradd = new UserServiceList();
			useradd.setUid(user.getUid());
			useradd.setStartdate(timeStamp);
			if(s.equals("Data Support")) {
				useradd.setUservicetype(UserServiceConstant.DATASUPPORT);
			}else if(s.equals("MIS")) {
				useradd.setUservicetype(UserServiceConstant.MIS);
			}else if(s.equals("Analytics")) {
				useradd.setUservicetype(UserServiceConstant.ANALYTICS);
			}else if(s.equals("MR")) {
				useradd.setUservicetype(UserServiceConstant.MR);
			}
			useradd.setUservicestatus(ActiveStatus.ACTIVE);
			userServiceListService.addNewService(useradd);
		}
		emailService.userAddesEmail(saveuser);
		}
		 catch (Exception e) {
			e.getStackTrace();
		}
		return true;
	}
	
	private RequestAccess updateRequestAccess(String uId) {
		RequestAccess req = requestaccessrepo.findByUid(uId);
		req.setUrequeststatus(ActiveStatus.ACTIVE);
		return requestaccessrepo.save(req);
	}

	@Override
	public UserProfile findUserProfile(String queryid , String password) {
		Boolean result;
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		UserProfile res = userprofilerepo.findByUqueryid(queryid);
		if(res != null) {
			if(password.equals(res.getUpassword())) {
				result = true;
			}else {
				result = false;
			}
		}else {
			result = false;
		}
		res.setUserlastlogin(timeStamp);
		userprofilerepo.save(res);
		if( result == true) {
			return res;
		}else {
			return null;
		}
	
	}

	@Override
	public UserProfile findUserProfileByUqueryid(String queryId) {
		UserProfile userprofile = userprofilerepo.findByUqueryid(queryId);
		return userprofile;
	}

	@Override
	public UserProfile saveUser(UserProfile user) {
		return userprofilerepo.save(user);
	}

	@Override
	public UserProfile addExistingUser(TOnboardExistingUser user) {
		UserProfile userprofile = userprofilerepo.findByUemailid(user.getUemailid());
		userprofile.setUqueryid(user.getUqueryid());
		userprofile.setUpassword(user.getUpassword());
		return userprofilerepo.save(userprofile);
	}

	@Override
	public ArrayList<String> getAllActiveUser() {
		ArrayList<String> uqid = new ArrayList<>();
		ArrayList<UserProfile> userlist = userprofilerepo.findByUprofilestatus(ActiveStatus.ACTIVE);
		Iterator<UserProfile> itr = userlist.iterator();
		while(itr.hasNext()) {
			uqid.add(itr.next().getUid());
		}
		return uqid;
	}

	@Override
	public String uniqueUqueryId(String uQueryId) {
		if(userprofilerepo.findByUqueryid(uQueryId) != null ) {
			return "PRESENT";
		}else {
			return "UNIQUE";
		}
	}

	@Override
	public void updateLastUploaded(String uid) {
		 UserProfile user = userprofilerepo.findByUid(uid);
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 user.setUlastfileuplode(timeStamp);
		 userprofilerepo.save(user);
	}
}
