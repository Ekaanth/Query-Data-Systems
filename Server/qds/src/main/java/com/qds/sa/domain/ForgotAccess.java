package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
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
	private String uid;
	private String uname;
	private String uemailid;
	private String umobilenumber;
	private String uipaddress;
	private Date sentdate;
	private ActiveStatus ustatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemailid() {
		return uemailid;
	}
	public void setUemailid(String uemailid) {
		this.uemailid = uemailid;
	}
	public String getUmobilenumber() {
		return umobilenumber;
	}
	public void setUmobilenumber(String umobilenumber) {
		this.umobilenumber = umobilenumber;
	}
	public String getUipaddress() {
		return uipaddress;
	}
	public void setUipaddress(String uipaddress) {
		this.uipaddress = uipaddress;
	}
	public Date getSentdate() {
		return sentdate;
	}
	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}
	public ActiveStatus getUstatus() {
		return ustatus;
	}
	public void setUstatus(ActiveStatus ustatus) {
		this.ustatus = ustatus;
	}
	
	
}
