package com.qds.sa.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.DataType;
import com.qds.sa.util.constant.UserServiceConstant;

@Entity
@Table(name="uploaduserdata")
public class UploadUserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uid;
	@Enumerated(EnumType.STRING)
	private UserServiceConstant uservice;
	@Enumerated(EnumType.STRING)
	private DataType udatatype;
	private String ufilename;
	private String timestamp;
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
	public UserServiceConstant getUservice() {
		return uservice;
	}
	public void setUservice(UserServiceConstant uservice) {
		this.uservice = uservice;
	}
	public DataType getUdataType() {
		return udatatype;
	}
	public void setUdataType(DataType udataType) {
		this.udatatype = udataType;
	}
	public String getUfilename() {
		return ufilename;
	}
	public void setUfilename(String ufilename) {
		this.ufilename = ufilename;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
