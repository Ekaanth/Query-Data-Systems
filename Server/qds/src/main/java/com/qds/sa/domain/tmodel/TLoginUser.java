package com.qds.sa.domain.tmodel;

import java.util.ArrayList;
import java.util.Date;

import com.qds.sa.domain.UserServiceList;

public class TLoginUser {

	private String uid;
	private String  uname;
	private String uemailid;
	private String umobilenumber;
	private String uprofilestatus;
	private Date ulastlogin;
	private ArrayList<UserServiceList> uservice;
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
	public String getUprofilestatus() {
		return uprofilestatus;
	}
	public void setUprofilestatus(String uprofilestatus) {
		this.uprofilestatus = uprofilestatus;
	}
	public Date getUlastlogin() {
		return ulastlogin;
	}
	public void setUlastlogin(Date ulastlogin) {
		this.ulastlogin = ulastlogin;
	}
	public ArrayList<UserServiceList> getUservice() {
		return uservice;
	}
	public void setUservice(ArrayList<UserServiceList> uservice) {
		this.uservice = uservice;
	}

	
}
