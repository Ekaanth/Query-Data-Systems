package com.qds.sa.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ActiveStatus;

@Entity
@Table(name="forgotaccess")
public class ForgotAccess {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uemailid;
	private String uipaddress;
	private String timestamp;
	@Enumerated(EnumType.STRING)
	private ActiveStatus ustatus;
	private String uipcountry;
	
	public String getUipcountry() {
		return uipcountry;
	}
	public void setUipcountry(String uipcountry) {
		this.uipcountry = uipcountry;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUemailid() {
		return uemailid;
	}
	public void setUemailid(String uemailid) {
		this.uemailid = uemailid;
	}
	public String getUipaddress() {
		return uipaddress;
	}
	public void setUipaddress(String uipaddress) {
		this.uipaddress = uipaddress;
	}
	public ActiveStatus getUstatus() {
		return ustatus;
	}
	public void setUstatus(ActiveStatus ustatus) {
		this.ustatus = ustatus;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
