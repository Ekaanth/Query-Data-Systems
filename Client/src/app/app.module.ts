import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Router} from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { AdminComponent } from './admin/admin.component';
import { ForgotComponent } from './forgot/forgot.component';
import { HomeComponent } from './home/home.component';
import { IndexComponent } from './index/index.component';
import { RequestComponent } from './request/request.component';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import { LoginService } from './service/login.service';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeService } from './service/home.service';
import { UserService } from './service/user.service';
import {SelectModule} from 'ng2-select';
import { FileUploadModule } from 'ng2-file-upload';
import { AdminService } from './service/admin.service';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    ForgotComponent,
    HomeComponent,
    IndexComponent,
    RequestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    FileUploadModule,
    CommonModule,
    HttpModule,SelectModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot({
      timeOut: 5000,
      positionClass: 'toast-bottom-right',
    }),
    RouterModule.forRoot([
      {path: '', component: IndexComponent},
        {path: 'home', component: HomeComponent},
      {path: 'admin', component: AdminComponent},
      {path: 'forgot', component: ForgotComponent},
      {path: 'requestaccess', component: RequestComponent}
    ])
  ],
  providers: [LoginService , HomeService , UserService, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
