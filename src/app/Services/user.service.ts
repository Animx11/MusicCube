import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Users } from '../Class/Users';
import { JwtResponse } from '../Class/JwtResponse';
import {Observable, from} from 'rxjs';
import { SignIn } from '../Class/user/SignIn';

import { api_url } from "./API_URL";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) { }

  signUp(obj: Users): Observable<string> {
    return this.http.post<string>(`${api_url}/auth/signup`, obj, httpOptions);
  }

  signIn(obj: SignIn): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${api_url}/auth/signin`, obj, httpOptions);
  }

  takeUserInfo(userName: string): Observable<Users> {
    return this.http.get<Users>(`${api_url}/user_by_userName?userName=${userName}`);
  }

  changeUser(obj: Users): Observable<Users> {
    return this.http.put<Users>(`${api_url}/edit`, obj);
  }
  changeUserPassword(obj: Users, oldPassword: string): Observable<Users> {
    return this.http.put<Users>(`${api_url}/changePassword?oldPassword=${oldPassword}`, obj);
  }

}
