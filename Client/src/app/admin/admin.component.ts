import { Component, OnInit } from '@angular/core';
import { HomeService } from '../service/home.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public adduser: FormGroup;
  public forgotpassword: FormGroup;
  userservice = [];
  public inactiveRequestedUser;
  items = ['Data Support', 'MIS' , 'Analytics' , 'MR'];
  constructor(private hs: HomeService, private fb: FormBuilder, private us: UserService, private ts: ToastrService) { }

  ngOnInit() {
    this.adduser = this.fb.group({
      uid: [],
      uqueryid: [null,  Validators.compose([Validators.required])],
      upassword: [null ,  Validators.compose([Validators.required])],
     // service : this.fb.array([])
    });


    this.forgotpassword = this.fb.group({
      uid: [],
      uqueryid: [null,  Validators.compose([Validators.required])],
      upassword: [null ,  Validators.compose([Validators.required])],
    });
  }

  getInactiveRequestedUsers() {
    this.hs.getInactiveRequestAccess().subscribe(data =>
      this.getRequestedUsers(data) , error => this.error(error));
  }

  getRequestedUsers(data) {
    this.inactiveRequestedUser = data;
    console.log(data);
  }

  // add user
  addUserFormSubmit(adduser) {
    const addUser = {
      'uid': this.adduser.controls.uid.value,
      'uqueryid': this.adduser.controls.uqueryid.value,
      'upassword': this.adduser.controls.upassword.value,
      'uservice' : JSON.stringify(this.userservice)
    };
    console.log(addUser);
   this.adduser.disable();
   this.us.addUser(addUser).subscribe(data => this.useradd(data) , error => this.error(error));
  }

  useradd(data) {
    this.ts.success('', 'User added');
    this.userservice = [];
    this.adduser.enable();
    this.adduser.reset();
  this.inactiveRequestedUser = [];
    this.hs.getInactiveRequestAccess().subscribe(resdata =>
      this.getRequestedUsers(resdata) , error => this.error(error));
  }

  error(data) {
console.log(data);
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


  forgotpasswordSubmit(forgotpassword) {
    console.log(forgotpassword);
  }
}
