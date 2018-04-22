package com.qds.sa.serviceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qds.sa.domain.UserServiceList;
import com.qds.sa.domain.tmodel.TaddDelService;
import com.qds.sa.jparepository.UserServiceListRep;
import com.qds.sa.service.UserServiceListService;
import com.qds.sa.util.constant.ActiveStatus;
import com.qds.sa.util.constant.UserServiceConstant;

@Service
@Transactional
public class UserServiceListServiceImp implements UserServiceListService{

	
	@Autowired
	UserServiceListRep userServiceListRep;
	@Override
	public UserServiceList addNewService(UserServiceList userServiceList) {
		return userServiceListRep.save(userServiceList);
	}
	
	
	@Override
	public ArrayList<UserServiceList> findByUid(String uid) {
		return userServiceListRep.findByUid(uid);
	}

	@Override
	public UserServiceList addService(TaddDelService userDetails) {
		 String[] myData = userDetails.getUservice().replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
		 for (String s: myData) {
			 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				if(s.equals("Data Support")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "DATASUPPORT");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.DATASUPPORT);
						useradd.setUservicestatus(ActiveStatus.ACTIVE);
						useradd.setStartdate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.INACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.DATASUPPORT);
						activetemp.setUservicestatus(ActiveStatus.ACTIVE);
						activetemp.setStartdate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("MIS")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "MIS");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.MIS);
						useradd.setUservicestatus(ActiveStatus.ACTIVE);
						useradd.setStartdate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.INACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.MIS);
						activetemp.setUservicestatus(ActiveStatus.ACTIVE);
						activetemp.setStartdate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("Analytics")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "ANALYTICS");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.ANALYTICS);
						useradd.setUservicestatus(ActiveStatus.ACTIVE);
						useradd.setStartdate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.INACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.ANALYTICS);
						activetemp.setUservicestatus(ActiveStatus.ACTIVE);
						activetemp.setStartdate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("MR")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "MR");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.MR);
						useradd.setUservicestatus(ActiveStatus.ACTIVE);
						useradd.setStartdate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.INACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.MR);
						activetemp.setUservicestatus(ActiveStatus.ACTIVE);
						activetemp.setStartdate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else {
					
				}
			}
		return null;
	}
	
	@Override
	public UserServiceList deleteService(TaddDelService userDetails) {
		String[] myData = userDetails.getUservice().replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
		 for (String s: myData) {
			 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				if(s.equals("Data Support")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "DATASUPPORT");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.DATASUPPORT);
						useradd.setUservicestatus(ActiveStatus.INACTIVE);
						useradd.setEnddate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.ACTIVE)){ 
						activetemp.setUservicetype(UserServiceConstant.DATASUPPORT);
						activetemp.setUservicestatus(ActiveStatus.INACTIVE);
						activetemp.setEnddate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("MIS")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "MIS");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.MIS);
						useradd.setUservicestatus(ActiveStatus.INACTIVE);
						useradd.setEnddate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.ACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.MIS);
						activetemp.setUservicestatus(ActiveStatus.INACTIVE);
						activetemp.setEnddate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("Analytics")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "ANALYTICS");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.ANALYTICS);
						useradd.setUservicestatus(ActiveStatus.ACTIVE);
						useradd.setEnddate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.ACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.ANALYTICS);
						activetemp.setUservicestatus(ActiveStatus.ACTIVE);
						activetemp.setEnddate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else if(s.equals("MR")) {
					UserServiceList activetemp = new UserServiceList();
					activetemp = userServiceListRep.findByUidAndServiceType(userDetails.getUid() , "MR");
					if(activetemp == null ) {
						UserServiceList useradd = new UserServiceList();
						useradd.setUid(userDetails.getUid());
						useradd.setUservicetype(UserServiceConstant.MR);
						useradd.setUservicestatus(ActiveStatus.INACTIVE);
						useradd.setEnddate(timeStamp);
						userServiceListRep.save(useradd);
					}else if(activetemp.getUservicestatus().equals(ActiveStatus.ACTIVE)){
						activetemp.setUservicetype(UserServiceConstant.MR);
						activetemp.setUservicestatus(ActiveStatus.INACTIVE);
						activetemp.setEnddate(timeStamp);
						userServiceListRep.save(activetemp);
					}else {
						
					}
				}else {
					
				}
			}
		return null;
}


	@Override
	public ArrayList<UserServiceList> findByUidAndStatus(String uid) {
		return userServiceListRep.findByUidAndStatus(uid);
	}
}
