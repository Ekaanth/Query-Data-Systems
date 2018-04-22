import { Component, OnInit } from '@angular/core';
import { HomeService } from '../service/home.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { ToastrService } from 'ngx-toastr';
import { AdminService } from '../service/admin.service';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public adduser: FormGroup;
  public forgotpassword: FormGroup;
  public addDeleteServive: FormGroup;
  public adminUpload: FormGroup;
  public userservice = [];
  public activeUserList = [];
  public inactiveRequestedUser;
  public forgotUserList;
  public items = ['Data Support', 'MIS' , 'Analytics' , 'MR'];
  public uploader = new FileUploader({});
  constructor(private hs: HomeService, private fb: FormBuilder, private us: UserService, private ts: ToastrService, private as: AdminService) { }

  ngOnInit() {
    this.adduser = this.fb.group({
      uid: [],
      uqueryid: [null,  Validators.compose([Validators.required])],
      upassword: [null ,  Validators.compose([Validators.required])],
     // service : this.fb.array([])
    });

    this.addDeleteServive = this.fb.group({
      uid: [null,  Validators.compose([Validators.required])],
      userviceRequest: [null,  Validators.compose([Validators.required])]
    });

    this.adminUpload = this.fb.group({
      userid: [null,  Validators.compose([Validators.required])]
    });

    this.forgotpassword = this.fb.group({
      uemailid: [null,  Validators.compose([Validators.required])],
      uqueryid: [null,  Validators.compose([Validators.required])],
      upassword: [null ,  Validators.compose([Validators.required])],
    });
  }

  getInactiveRequestedUsers() {
    this.as.getInactiveRequestAccess().subscribe(data =>
      this.getRequestedUsers(data) , error => this.error(error));
  }

  getRequestedUsers(data) {
    console.log(data);
    this.inactiveRequestedUser = JSON.parse(data._body);
    
  }

  addUserFormSubmit(adduser) {
    if(this.userservice.length > 0){
      this.adduser.disable();
      this.as.uniqueUqueryId(this.adduser.controls.uqueryid.value).subscribe(data => this.addNewUser(data) , error => this.error(error));
    }else{
      this.ts.error('', 'Please select service');
    }
  }

  usernametaken(){
    this.ts.error('', 'Username taken');
    this.adduser.enable();
    this.forgotpassword.enable();
  }

  addNewUser(data){
    console.log(data);
    if(data._body === 'UNIQUE'){
      const addUser = {
        'uid': this.adduser.controls.uid.value,
        'uqueryid': this.adduser.controls.uqueryid.value,
        'upassword': this.adduser.controls.upassword.value,
        'uservice' : JSON.stringify(this.userservice)
      };
      this.us.addUser(addUser).subscribe(data => this.useradd(data) , error => this.error(error));
    }else{
      this. usernametaken();
    }
  }

  useradd(data) {
    this.ts.success('', 'User added');
    this.userservice = [];
    this.adduser.enable();
    this.adduser.reset();
    this.inactiveRequestedUser = [];
    this.as.getInactiveRequestAccess().subscribe(resdata =>
    this.getRequestedUsers(resdata) , error => this.error(error));
  }

  error(data) {
this.ts.error('', 'Unable to connect to server');
this.adduser.enable();
  }

  public selected(value: any): void {
    this.userservice.push(value.text);
  }

  public removed(value: any): void {
    for (let index = 0; index <  this.userservice.length; index++) {
     if (this.userservice[index] === value.text ) {
        this.userservice.splice(index , 1);
     }
    }
  }

  
  getAlltheForgotList() {
    this.forgotpassword.reset();
    this.as.findAllForgotUser().subscribe(data => this.alltheForgotList(data) , error => this.ts.error('', 'Unable to connect to server'));
  }  

  alltheForgotList(data){
    this.forgotUserList = JSON.parse(data._body);
  }

  forgotpasswordSubmit(forgotpassword) {
    this.forgotpassword.disable();
    this.as.uniqueUqueryId(this.forgotpassword.controls.uqueryid.value).subscribe(data => this.addNewForgotUser(data) , error => this.error(error));
  }

  addNewForgotUser(data){
    if(data._body === 'UNIQUE'){
      const userDetails = {
        'uemailid': this.forgotpassword.controls.uemailid.value,
        'uqueryid': this.forgotpassword.controls.uqueryid.value,
        'upassword': this.forgotpassword.controls.upassword.value
      }
      this.forgotUserList = [];
      this.as.onboardExistingUser(userDetails).subscribe(data => {this.forgotpassword.enable(); this.forgotpassword.reset()} , error => {this.ts.error('', 'Unable to connect to server') , this.forgotpassword.enable()});
      this.as.findAllForgotUser().subscribe(data => this.alltheForgotList(data) , error => this.ts.error('', 'Unable to connect to server'));
    }else{
      this.ts.error('', 'Username taken');
      this.forgotpassword.enable();
    }
  }

  addDeleteServiveSubmit(addDeleteServive){ 
    this.addDeleteServive.disable();
    const addUser = {
      'uid': this.addDeleteServive.controls.uid.value,
      'userviceRequest': this.addDeleteServive.controls.userviceRequest.value,
      'uservice' : JSON.stringify(this.userservice)
    };
    this.as.addDeleteservice(addUser).subscribe(data => this.serviceAdd(data) , error => this.ts.error('' , 'Connection error'));
  }
 
  getExistingUsers(){
    this.as.getAllActiveUser().subscribe(data => this.existingUsers(data) , error => this.ts.error('','Connection Error') );
  }

  serviceAdd(data){
    this.addDeleteServive.enable();
    this.addDeleteServive.reset();
    this.ts.success('', 'Service request sent');
  }

  existingUsers(data){
    this.activeUserList = JSON.parse(data._body);
  }

  adminUploadSubmit(adminUpload){
    let data = new FormData();
    if(this.uploader.queue.length >= 1){
      data.append('file', this.uploader.queue[0]._file);
      data.append( 'uid', this.adminUpload.controls.userid.value);
      this.as.uploadAdminDocuments(data).subscribe(data => {this.ts.success('','File Successfully Uploded'); this.adminUpload.reset();
      this.uploader.clearQueue();} , error => this.ts.error('','File could not be uploded'));
    }else{
      this.ts.error('','Please select a ZIP file')
    }
  }
}
