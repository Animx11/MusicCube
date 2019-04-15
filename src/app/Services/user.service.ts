import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Users} from '../Class/Users';
import {Observable} from 'rxjs';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) { }

  signUp(obj: Users): Observable<any> {
    return this.http.post(`${apiUrl}/users`, obj);
  }

}
