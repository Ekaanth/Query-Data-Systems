package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.LoginResult;

@Entity
@Table(name="loginaccess")
public class LoginAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uqueryid;
	private String upassword;
	private String uipaddress;
	private String uipcountry;
	@Enumerated(EnumType.STRING)
	private LoginResult uresult;
	private Date timestamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUipaddress() {
		return uipaddress;
	}
	public void setUipaddress(String uipaddress) {
		this.uipaddress = uipaddress;
	}
	public LoginResult getUresult() {
		return uresult;
	}
	public void setUresult(LoginResult uResult) {
		this.uresult = uResult;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getUipcountry() {
		return uipcountry;
	}
	public void setUipcountry(String uipcountry) {
		this.uipcountry = uipcountry;
	}
	public String getUqueryid() {
		return uqueryid;
	}
	public void setUqueryid(String uqueryid) {
		this.uqueryid = uqueryid;
	}
	
	
	
}
