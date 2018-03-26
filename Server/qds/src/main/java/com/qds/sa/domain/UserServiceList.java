package com.qds.sa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.UserServiceConstant;

	@Entity
	@Table(name="userprofile")
	public class UserServiceList {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String uid;
		private UserServiceConstant uservicetype;
		private ActiveStatus uservicestatus;
		private String ulastpayment;
		private Date startdate;
		private Date enddate;
		private String filename;
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
		public ActiveStatus getUservicestatus() {
			return uservicestatus;
		}
		public void setUservicestatus(ActiveStatus uservicestatus) {
			this.uservicestatus = uservicestatus;
		}
		public String getUlastpayment() {
			return ulastpayment;
		}
		public void setUlastpayment(String ulastpayment) {
			this.ulastpayment = ulastpayment;
		}
		public Date getStartdate() {
			return startdate;
		}
		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}
		public Date getEnddate() {
			return enddate;
		}
		public void setEnddate(Date enddate) {
			this.enddate = enddate;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		
		
}
