package com.qds.sa.service;

import com.qds.sa.domain.LoginAccess;
import com.qds.sa.domain.tmodel.TLoginUser;

public interface LoginAccessService {

	public TLoginUser addLoginDetails(LoginAccess details);
}
