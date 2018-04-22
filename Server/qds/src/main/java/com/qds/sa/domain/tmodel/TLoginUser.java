package com.qds.sa.domain.tmodel;

import java.util.ArrayList;
import java.util.Date;

import com.qds.sa.domain.UserServiceList;

public class TLoginUser {

	private String uid;
	private String uname;
	private String uemailid;
	private String umobilenumber;
	private String ulastuploded;
	private String ulastpayment;
	private String ulastlogin;
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
	public String getUlastuploded() {
		return ulastuploded;
	}
	public void setUlastuploded(String ulastuploded) {
		this.ulastuploded = ulastuploded;
	}
	public String getUlastpayment() {
		return ulastpayment;
	}
	public void setUlastpayment(String ulastpayment) {
		this.ulastpayment = ulastpayment;
	}
	public String getUlastlogin() {
		return ulastlogin;
	}
	public void setUlastlogin(String ulastlogin) {
		this.ulastlogin = ulastlogin;
	}
	public ArrayList<UserServiceList> getUservice() {
		return uservice;
	}
	public void setUservice(ArrayList<UserServiceList> uservice) {
		this.uservice = uservice;
	}

}
