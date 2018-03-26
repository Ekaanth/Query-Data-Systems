package com.qds.sa.service;

import com.qds.sa.domain.UserProfile;

public interface UserProfileService {

	public UserProfile addUser (UserProfile user);
	
	public Boolean findUserProfile(String queryId , String password);
	
	public UserProfile findUserProfileByUqueryid(String queryId);

	public UserProfile updateLogintime(String uqueryid);
}
