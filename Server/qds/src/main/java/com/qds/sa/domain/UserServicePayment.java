package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.qds.sa.util.constant.PaymentStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Entity
public class UserServicePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uid;
	@Enumerated(EnumType.STRING)
	private UserServiceConstant uservicetype;
	private String paymentcost;
	private PaymentStatus paymentstatus;
	private Date timestamp;
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
	public UserServiceConstant getUservicetype() {
		return uservicetype;
	}
	public void setUservicetype(UserServiceConstant uservicetype) {
		this.uservicetype = uservicetype;
	}
	public String getPaymentcost() {
		return paymentcost;
	}
	public void setPaymentcost(String paymentcost) {
		this.paymentcost = paymentcost;
	}
	public PaymentStatus getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(PaymentStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
