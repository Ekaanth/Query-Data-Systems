import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import * as global from '../global.variable';

@Injectable()
export class HomeService {

    private base_url: string;
    private headers;
    constructor(private http: Http) {
        this.base_url = global.base_url;
    }

    contactus(contactDet) {
        const url = `${global.base_url}contactus`;
        return this.http.post(url, contactDet).map((response: Response) => response.json());
    }

    getInactiveRequestAccess() {
        const url = `${global.base_url}getInactiveRequestAccess`;
        return this.http.get(url).map(res => res.json());
    }
}
