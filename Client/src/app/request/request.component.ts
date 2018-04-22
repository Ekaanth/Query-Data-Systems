import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css'],
  providers: []
})
export class RequestComponent implements OnInit {

  public requestAccesss: FormGroup;
  sysDetails;
  constructor(private fb: FormBuilder, private ls: LoginService, private toastr: ToastrService) { }

  ngOnInit() {
    let emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
    let mobileNumber = "^[0-9]{10,10}$";
    let name = "^[A-Za-z]{3,15}$"
    this.requestAccesss = this.fb.group({
      uname: [null, Validators.compose([Validators.required, Validators.pattern(name)])],
      uemailid: [null, Validators.compose([Validators.required , Validators.pattern(emailPattern)]) ],
      umobilenumber: [null, Validators.compose([Validators.required , Validators.pattern(mobileNumber)])],
      uipaddress: [null]
    });
    this.sysDetails = this.ls.getSystemIp();
  }
  requestAccesssSubmit(requestAccesssSubmit) {
    const res = {
      'uname': requestAccesssSubmit.controls.uname.value,
      'uemailid': requestAccesssSubmit.controls.uemailid.value,
      'umobilenumber': requestAccesssSubmit.controls.umobilenumber.value,
      'uipaddress': this.sysDetails.ip,
      'uipcountry': this.sysDetails.country_name
    };
    this.requestAccesss.disable();
   this.ls.requestAccess(res).subscribe(data => this.sentRequest(data), error => this.error(error));
  }

  sentRequest(data) {
    this.requestAccesss.reset();
    this.requestAccesss.enable();
    this.toastr.success('', 'Access Request Sent');
  }

  error(error) {
    this.requestAccesss.disable();
    this.toastr.error('', 'Could not connect to server');
  }
}
