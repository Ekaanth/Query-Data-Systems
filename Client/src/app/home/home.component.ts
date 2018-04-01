import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { HomeService } from '../service/home.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [HomeService]
})
export class HomeComponent implements OnInit {

  enquiry: FormGroup;
  contactus: FormGroup;
  sysDetails;
  constructor(private fb: FormBuilder , private ls: LoginService , private hs: HomeService,
     private ts: ToastrService, private router: Router) { }

  ngOnInit() {
    if (localStorage.getItem('userDetail')) {
      this.contactus = this.fb.group({
        uname: [null, Validators.compose([Validators.required])],
        uemailid: [null, Validators.compose([Validators.required])],
        umobilenumber: [null, Validators.compose([Validators.required])],
        urequirement: [null, Validators.compose([Validators.required])]
      });
      this.enquiry = this.fb.group({
        uservicestatus: [null],
        uservicetype: [null],
        uquery: [null, Validators.compose([Validators.required])],
        ufeedback: [null, Validators.compose([Validators.required])]
      });
      this.sysDetails = this.ls.getCurrentUser();
      this.ls.updataLogintime(localStorage.getItem('userDetail')).subscribe();
  } else {
    this.router.navigateByUrl('/');
  }
  }

  contactFormaSubmit(contactus) {
    const details = {
      'uname': this.contactus.controls.uname.value,
      'uemailid': this.contactus.controls.uemailid.value,
      'umobilenumber': this.contactus.controls.umobilenumber.value,
      'urequirement': this.contactus.controls.urequirement.value,
      'uipaddress': this.sysDetails.ip
    };
    this.contactus.disable();
    this.hs.contactus(details).subscribe(data => this.contactusSuccess(data), error => this.error(error));
  }

  contactusSuccess(data) {
    this.contactus.reset();
    this.contactus.enable();
    this.ts.success('' , 'Details has been sent');
  }

  error(data) {
    this.contactus.enable();
    this.ts.error('', 'Unable to connect');
  }

  enquiryFormSubmit(enquiry) {
    console.log(enquiry.value);
  }

  logoutClick() {
    localStorage.removeItem('uqueryid');
    this.router.navigateByUrl('/');
  }
}
