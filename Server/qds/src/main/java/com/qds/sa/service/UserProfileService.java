package com.qds.sa.service;

import com.qds.sa.domain.UserProfile;

public interface UserProfileService {

	public boolean addUser (UserProfile user) throws Exception;
	
	public UserProfile findUserProfile(String queryId , String password);
	
	public UserProfile findUserProfileByUqueryid(String queryId);

	public UserProfile updateLogintime(String uqueryid);
}
