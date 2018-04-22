import { Component, OnInit, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { HomeService } from '../service/home.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { FileUploader } from 'ng2-file-upload';
declare const sha512: any;

///<reference path="node_modules/crypto-js/sha512.js"/> 

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [HomeService]
})
export class HomeComponent implements OnInit {

  public enquiry: FormGroup;
  public contactus: FormGroup;
  public uploadform:FormGroup;
  public payuform:FormGroup;
  public sysDetails;
  public username;
  public serviceType;
  public uLastlogin;
  public uLastPayment
  public ulastUpload;
  public randomNumber;
  public uploader:FileUploader = new FileUploader({
    isHTML5: true
  });
  constructor(private fb: FormBuilder , private ls: LoginService , private hs: HomeService, private us: UserService,
     private ts: ToastrService, private router: Router) { }

  ngOnInit() {
    if (localStorage.getItem('userDetail')) {
      this.username = this.ls.getCurrentUser().uname;
      this.uLastlogin = this.ls.getCurrentUser().ulastlogin;
      this.ulastUpload = this.ls.getCurrentUser().ulastuploded;
      this.uLastPayment = this.ls.getCurrentUser().ulastpayment;
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

      this.uploadform = this.fb.group({
        type:  [null, Validators.compose([Validators.required])]
      });

      this.payuform = this.fb.group({
        productinfo: [null, Validators.compose([Validators.required])],
        amount: [null, Validators.compose([Validators.required])],
        firstname: [null, Validators.compose([Validators.required])],
        email: [null, Validators.compose([Validators.required])],
        phone: [null, Validators.compose([Validators.required])]
      });
      this.sysDetails = this.ls.getCurrentUser();
      
      this.serviceType = localStorage.getItem('servicetype');
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
    console.log(enquiry);
    const enquery = {
      'uid': this.sysDetails.uid,
      'servicetype' : this.enquiry.controls.uservicestatus.value,
      'servicestatus': this.enquiry.controls.uservicestatus.value,
      'uquery' : this.enquiry.controls.uservicestatus.value,
      'ufeedback': this.enquiry.controls.uservicestatus.value,
      'uipaddress': this.ls.getSystemIp().ip,
      'uipcountry': this.ls.getSystemIp().country_name
    }
    this.hs.userenqueary(enquery).subscribe(data => this.enquirySubmit(data) , error => this.error(error));
    this.enquiry.reset();
    }

    enquirySubmit(data){
      this.ts.success('','Enquiry sent');
    }

  logoutClick() {
    localStorage.removeItem('userDetail');
    this.router.navigateByUrl('/');
  }

  uploadSubmit(){
    if(localStorage.getItem('servicetype')){
      let userDetails ;
      this.uploadform.disable();
      if(localStorage.getItem('servicetype') !== '') {
          for (var i = 0; i < this.uploader.queue.length; ) {
            let data = new FormData();
            data.append('file', this.uploader.queue[i]._file);
            data.append( 'uid', this.sysDetails.uid);
          data.append( 'dataType', this.uploadform.controls.type.value);
          data.append('serviceType' ,localStorage.getItem('servicetype'))
          this.hs.uploadFile(data).subscribe(data => this.dataUpload(data) , error => console.log(data));
          i++
        }
        this.uploadform.reset();
      this.uploader.clearQueue();
      this.uploadform.enable();
        }
    } else{
      this.ts.error('','Select servicetype');
    }
  }

  dataUpload(data){
    if(data._body === "YES"){
      this.ts.success("","File uploded");
    }else{
      this.ts.error("","File not uploded");
    }
    return 0;
  }
  serviceTypeChanged(event){
    localStorage.setItem('servicetype' ,  event.target.value);
  }

  downloadZipFile(){
    window.location.assign(this.hs.download(this.us.getLoginUserDetail().uid));
  }

    gen_random() {
      var text = "";
      var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
      for (var i = 0; i < 20; i++){
        text += possible.charAt(Math.floor(Math.random() * possible.length));
      }
    this.randomNumber = text;
}

payuformSubmit(payuform){
  this.gen_random();
  let key ="BoSpd5QD"
  let salt = "dcCrEupiBc"
  var hash = new sha512.update( key + '|' + this.randomNumber +'|'+ 10 + '|' + this.payuform.controls.productinfo.value + '|' +this.payuform.controls.firstname.value+'|'+this.payuform.controls.email.value+'|||||||||||'+salt);
  const payuForm = {
    key: key,
    txnid: this.randomNumber,
    amount: 10,
    productinfo: this.payuform.controls.productinfo.value,
    firstname: this.payuform.controls.firstname.value,
    email: this.payuform.controls.email.value,
    phone: this.payuform.controls.phone.value,
    surl: 'https://www.google.co.in/?gfe_rd=cr&ei=Wpt_WI7TAYPy8Afo_JK4BA',
    furl: 'https://duckduckgo.com/',
    hash: hash.hex(),
    service_provider : "payu_paisa",
    salt : salt
    }
    this.hs.makePayment(payuForm).subscribe(data => this.paymentWindow(data) , error => console.log(error));
}

paymentWindow(data){
  window.location.assign(data._body);
}
}  