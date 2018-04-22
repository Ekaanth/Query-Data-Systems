package com.qds.sa.service;

import java.util.ArrayList;

import com.qds.sa.domain.UserProfile;
import com.qds.sa.domain.tmodel.TOnboardExistingUser;

public interface UserProfileService {

	public boolean addUser (UserProfile user) throws Exception;
	
	public UserProfile findUserProfile(String queryId , String password);
	
	public UserProfile findUserProfileByUqueryid(String queryId);
	
	public UserProfile saveUser(UserProfile user);
	
	public UserProfile addExistingUser(TOnboardExistingUser user);

	public ArrayList<String> getAllActiveUser();

	public String uniqueUqueryId(String uQueryId);

	public void updateLastUploaded(String uid);
}
