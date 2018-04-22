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
    let emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
    this.forgotAccess = this.fb.group({ 
      uemailid: [null, Validators.compose([Validators.required, Validators.pattern(emailPattern)])]
    });
  }

  forgotAccesssSubmit(forgotAccess) {
    const forgot = {
      'uemailid': this.forgotAccess.controls.uemailid.value,
      'uipaddress': this.ls.getSystemIp().ip,
      'uipcountry': this.ls.getSystemIp().country_name
    };
    console.log(forgot);
    this.forgotAccess.disable();
    this.ls.forgotAccess(forgot).subscribe(data => this.forgot(data), error => this.error(error));
  }

  error(error) {
    this.forgotAccess.enable();
    this.toastr.error('', 'Could not connect to server');
  }

  forgot(data) {
    this.forgotAccess.reset();
    this.forgotAccess.enable();
    this.toastr.success('', 'Access Request Sent');
  }
}
