package com.qds.sa.domain.tmodel;

import java.util.Date;

public class TEnquiry {

	private String uid;
	private String uemailid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUemailid() {
		return uemailid;
	}
	public void setUemailid(String uemailid) {
		this.uemailid = uemailid;
	}
	private String servicetype;
	private String servicestatus;
	private String uquery;
	private String ufeedback;
	private String uipaddress;
	private String uipcountry;
	private Date timestamp;
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getServicestatus() {
		return servicestatus;
	}
	public void setServicestatus(String servicestatus) {
		this.servicestatus = servicestatus;
	}
	public String getUquery() {
		return uquery;
	}
	public void setUquery(String uquery) {
		this.uquery = uquery;
	}
	public String getUfeedback() {
		return ufeedback;
	}
	public void setUfeedback(String ufeedback) {
		this.ufeedback = ufeedback;
	}
	public String getUipaddress() {
		return uipaddress;
	}
	public void setUipaddress(String uipaddress) {
		this.uipaddress = uipaddress;
	}
	public String getUipcountry() {
		return uipcountry;
	}
	public void setUipcountry(String uipcountry) {
		this.uipcountry = uipcountry;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
