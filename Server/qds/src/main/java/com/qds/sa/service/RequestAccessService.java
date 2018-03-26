package com.qds.sa.service;

import java.util.List;

import com.qds.sa.domain.RequestAccess;

public interface RequestAccessService {

	public RequestAccess registerUser(RequestAccess requestAccess);

	public List<RequestAccess> getAllInactiveStatus();
}
