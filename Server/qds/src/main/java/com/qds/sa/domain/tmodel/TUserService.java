package com.qds.sa.domain.tmodel;

import java.util.Date;

public class TUserService {

	private String uservice;
	private Date uservicelastpayment;
	private Date uservicelastuplode;
	
	
	public String getUservice() {
		return uservice;
	}
	public void setUservice(String uservice) {
		this.uservice = uservice;
	}
	public Date getUservicelastpayment() {
		return uservicelastpayment;
	}
	public void setUservicelastpayment(Date uservicelastpayment) {
		this.uservicelastpayment = uservicelastpayment;
	}
	public Date getUservicelastuplode() {
		return uservicelastuplode;
	}
	public void setUservicelastuplode(Date uservicelastuplode) {
		this.uservicelastuplode = uservicelastuplode;
	}
	
}
