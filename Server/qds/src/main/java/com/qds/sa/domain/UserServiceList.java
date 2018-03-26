package com.qds.sa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qds.sa.util.constant.ActiveStatus;

	@Entity
	@Table(name="userprofile")
	public class UserServiceList {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String uid;
		private String uservicetype;
		private ActiveStatus uservicestatus;
		private String ulastpayment;
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
		public String getUservicetype() {
			return uservicetype;
		}
		public void setUservicetype(String uservicetype) {
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
		
		
}
