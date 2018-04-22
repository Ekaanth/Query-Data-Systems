package com.qds.sa.service;

import com.qds.sa.domain.AdminFileUpload;

public interface AdminFileUploadService {

	public AdminFileUpload addnewFile(AdminFileUpload admin);

	public AdminFileUpload findPathByUid(String uid);

}
