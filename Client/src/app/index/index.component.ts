import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  public userlogin: FormGroup;
  public sysDetails;
  public loginErrorBoolean = false;
  constructor(private fb: FormBuilder , private ls: LoginService, private ts: ToastrService, private router: Router) { }

  ngOnInit() {
    this.userlogin = this.fb.group({
      uqueryid: [null,  Validators.compose([Validators.required])],
      upassword: [null,  Validators.compose([Validators.required])]
    });

    this.sysDetails = this.ls.getCurrentUser();
  }

  userloginSubmit(userlogin) {
    const loginUser = {
      'uqueryid': this.userlogin.controls.uqueryid.value,
      'upassword': this.userlogin.controls.upassword.value,
      'uipaddress': this.sysDetails.ip,
      'uipcountry': this.sysDetails.country_name
    };
    this.userlogin.disable();
    this.ls.userlogin(loginUser).subscribe(data => this.userlogindetails(data), error => this.loginError(error));
  }

  userlogindetails(data) {
      this.ts.success('', 'User Login Successfully');
      this.loginErrorBoolean = false;
      localStorage.setItem('userDetail' , JSON.stringify(data));
      this.router.navigateByUrl('/home');
    this.userlogin.enable();
  }
 
  loginError(error) {
    this.loginErrorBoolean = true;
    this.userlogin.enable();
    this.ts.error('', 'User Login Unsuccessfully');
  }
}
