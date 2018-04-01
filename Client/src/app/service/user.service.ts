import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import * as global from '../global.variable';


@Injectable()
export class UserService {

    private base_url: string;
    private headers;
    constructor(private http: Http) {
        this.base_url = global.base_url;
    }

    getLoginUserDetail(){
        return JSON.parse(localStorage.getItem('userDetail'));
    }

    addUser(user) {
        const url = `${global.base_url}user/adduser`;
        return this.http.post(url, user).map(res => res);
    }
} 
