import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import * as global from '../global.variable';

@Injectable()
export class LoginService {

    private base_url: string;
    private headers;
    constructor(private http: Http) {
        this.base_url = global.base_url;
    }

    getCurrentUser(): any {
      return JSON.parse(localStorage.getItem('userDetail'));
    }

    getSystemIp():any {
      return JSON.parse(localStorage.getItem('ipDetails'));
    }

    getNews() {
        const sysDateTime = new Date();
        const fulldatetime = sysDateTime.getTime();
        const url = `${global.base_url}NewsService/getNews?userid=${fulldatetime}&sysdatetime=${fulldatetime}`;
        return this.http.get(url).map(res => res);
      }

      saveRole(role) {
        const sysDateTime = new Date();
        const fulldatetime = sysDateTime.getTime();
        const url = `${global.base_url}UserService/saveRole`;
        return this.http.post(url, role).map(res => res);
      }

      requestAccess(access) {
        const sysDateTime = new Date();
        const fulldatetime = sysDateTime.getTime();
        const url = `${global.base_url}requestAccess`;
        return this.http.post(url, access).map((response: Response) => response);
      }

      forgotAccess(access) {
        const sysDateTime = new Date();
        const fulldatetime = sysDateTime.getTime();
        const url = `${global.base_url}forgotLogin`;
        return this.http.post(url, access).map((response: Response) => response);
      }

      userlogin(user) {
        const url = `${global.base_url}userlogin`;
        return this.http.post(url, user).map((response: Response) => response);
      }

      userLogindetails(quid) {
        const url = `${global.base_url}findByUqueryId/${quid}`;
        return this.http.get(url).map(res => res);
      }
}
