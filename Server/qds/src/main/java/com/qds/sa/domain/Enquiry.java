package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ServiceStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Entity
@Table(name="enquiry")
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uid;
	@Enumerated(EnumType.STRING)
	private UserServiceConstant servicetype;
	@Enumerated(EnumType.STRING)
	private ServiceStatus servicestatus;
	private String uquery;
	private String ufeedback;
	private String uipaddress;
	private String uipcountry;
	private String timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserServiceConstant getServicetype() {
		return servicetype;
	}
	public void setServicetype(UserServiceConstant servicetype) {
		this.servicetype = servicetype;
	}
	public ServiceStatus getServicestatus() {
		return servicestatus;
	}
	public void setServicestatus(ServiceStatus servicestatus) {
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
