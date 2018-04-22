import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import * as global from '../global.variable';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class HomeService {

    private base_url: string;
    private headers;
    constructor(private http: Http) {
        this.base_url = global.base_url;
    }

    contactus(contactDet) {
        const url = `${global.base_url}home/contactus`;
        return this.http.post(url, contactDet).map(response => response);
    }

    userenqueary(userEnquiry) {
        const url = `${global.base_url}home/userEnquiry`;
        return this.http.post(url , userEnquiry).map(res => res);
    }

    uploadFile(userUpload) {
        const url = `${global.base_url}home/uploadFile`;
        return this.http.post(url , userUpload).map(res => res);
    }

    download(uid){
        const url = `${global.base_url}home/download/${uid}`;
        return url;
    }

    makePayment(payment){
        const headers = new Headers();
        headers.append('Authorization', `bTeJLvRdpPXPiVLvNtZzlBzwIkZyP3OX812SxNEYTdw=`);
        let options = new RequestOptions({ headers: headers });
        const url = 'https://secure.payu.in/_payment';
        return this.http.post(url , payment , options).map(res => res);
    } 
}
