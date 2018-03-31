import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {

  forgotAccess: FormGroup;
  sysDetails;
  constructor(private fb: FormBuilder, private ls: LoginService, private toastr: ToastrService) { }

  ngOnInit() {
    this.forgotAccess = this.fb.group({
      uname: [null, Validators.compose([Validators.required])],
      uemailid: [null, Validators.compose([Validators.required])],
      umobilenumber: [null, Validators.compose([Validators.required])],
      uid: [null],
      uipaddress: [null]
    });
    this.sysDetails = this.ls.getCurrentUser();
  }

  forgotAccesssSubmit(forgotAccess) {
    const forgot = {
      'uname': this.forgotAccess.controls.uname.value,
      'uemailid': this.forgotAccess.controls.uemailid.value,
      'umobilenumber': this.forgotAccess.controls.umobilenumber.value,
      'uid': this.forgotAccess.controls.uid.value,
      'uipaddress': this.sysDetails.ip
    };
    this.ls.forgotAccess(forgot).subscribe(data => this.forgot(data), error => this.error(error));
  }

  error(error) {
    this.toastr.error('', 'Could not connect to server');
  }

  forgot(data) {
    this.forgotAccess.reset();
    this.toastr.success('', 'Access Request Sent');
  }
}
