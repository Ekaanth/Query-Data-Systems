package com.qds.sa.domain.tmodel;

import java.util.ArrayList;
import java.util.Date;

public class TLoginUser {

	private String uid;
	private String  uname;
	private String uemailid;
	private String umobilenumber;
	private String uprofilestatus;
	private Date ulastlogin;
	private ArrayList<TUserService> uservice;
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
	public ArrayList<TUserService> getUservice() {
		return uservice;
	}
	public void setUservice(ArrayList<TUserService> uservice) {
		this.uservice = uservice;
	}
	
}
