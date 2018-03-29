package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Entity
@Table(name="userprofile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uid;
	private String uqueryid;
	private String uname;
	@Column(name="uemailid")
	private String uemailid;
	@Column(name="umobilenumber")
	private String umobilenumber;
	private String upassword;
	private String ustorearea;
	private String uservice;
	@Enumerated(EnumType.STRING)
	private ActiveStatus uprofilestatus;
	private Date ustarteddate;
	private Date userlastlogin;
	
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
	public String getUqueryid() {
		return uqueryid;
	}
	public void setUqueryid(String uqueryid) {
		this.uqueryid = uqueryid;
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
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUstorearea() {
		return ustorearea;
	}
	public void setUstorearea(String ustorearea) {
		this.ustorearea = ustorearea;
	}
	public ActiveStatus getUprofilestatus() {
		return uprofilestatus;
	}
	public void setUprofilestatus(ActiveStatus uprofilestatus) {
		this.uprofilestatus = uprofilestatus;
	}
	public Date getUstarteddate() {
		return ustarteddate;
	}
	public void setUstarteddate(Date ustarteddate) {
		this.ustarteddate = ustarteddate;
	}
	public Date getUserlastlogin() {
		return userlastlogin;
	}
	public void setUserlastlogin(Date userlastlogin) {
		this.userlastlogin = userlastlogin;
	}
	public String getUservice() {
		return uservice;
	}
	public void setUservice(String uservice) {
		this.uservice = uservice;
	}

	
}
