import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import * as global from '../global.variable';

@Injectable()
export class AdminService {

    private base_url: string;
    private headers;
    constructor(private http: Http) {
        this.base_url = global.base_url;
    }

    findAllForgotUser() {
        const url = `${global.base_url}admin/findAllForgotUser`;
        return this.http.get(url).map(res => res);
    }

    getInactiveRequestAccess() {
        const url = `${global.base_url}admin/getInactiveRequestAccess`;
        return this.http.get(url).map(res => res);
    }
    onboardExistingUser(userDetails) {
        const url = `${global.base_url}admin/onboardExistingUser`;
        return this.http.post(url , userDetails).map(res => res);
    }

    getAllActiveUser() {
        const url = `${global.base_url}admin/getAllActiveUser`;
        return this.http.get(url).map(res => res);
    }

    addDeleteservice(userDetail) {
        const url = `${global.base_url}admin/addDeleteservice`;
        return this.http.post(url , userDetail).map(res => res);
    }

    uploadAdminDocuments(file) {
        const url = `${global.base_url}admin/uploadAdminDocuments`;
        return this.http.post(url , file).map(res => res);
    } 

    uniqueUqueryId(quserid) {
        const url = `${global.base_url}admin/uniqueUqueryId/quserid/${quserid}`;
        return this.http.get(url).map(res => res);
    }
}