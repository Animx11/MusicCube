import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Users } from '../Class/Users';
import { JwtResponse } from '../Class/JwtResponse';
import {Observable, from} from 'rxjs';
import { SignIn } from '../Class/SignIn';

import { api_url } from "../Utils/API_URL";
import { UserAccount } from '../Class/UserAccount';
import { UserProfile } from '../Class/UserProfile';
import { UserManage } from '../Class/UserManage';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) { }

  signUp(obj: UserAccount): Observable<string> {
    return this.http.post<string>(`${api_url}/auth/signup`, obj, httpOptions);
  }

  signIn(obj: SignIn): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${api_url}/auth/signin`, obj, httpOptions);
  }

  takeUserInfo(userName: string): Observable<Users> {
    return this.http.get<Users>(`${api_url}/user_by_userName?userName=${userName}`);
  }

  deleteYourAccount(userName: string, password: string): Observable<any> {
    return this.http.delete(`${api_url}/user?userName=${userName}&password=${password}`);
  }

  /*User Profile*/

  takeUserProfileInfo(userName: string): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${api_url}/userProfile_by_userName?userName=${userName}`);
  }

  changeUserProfile(obj: UserProfile): Observable<UserProfile> {
    return this.http.put<UserProfile>(`${api_url}/edit_userProfile`, obj);
  }

  /*User Account*/

  changeUserName(newUserName: string, userAccount: UserAccount): Observable<any> {
    return this.http.put(`${api_url}/edit_userName?newUserName=${newUserName}`, userAccount);
  }

  changeEmail(userAccount: UserAccount): Observable<any> {
    return this.http.put(`${api_url}/edit_email`, userAccount);
  }

  changePassword(userAccount: UserAccount, newPassword: string): Observable<any> {
    return this.http.put(`${api_url}/edit_password?newPassword=${newPassword}`, userAccount);
  }

  changeUser(obj: Users): Observable<Users> {
    return this.http.put<Users>(`${api_url}/edit`, obj);
  }
  changeUserPassword(obj: Users, oldPassword: string): Observable<Users> {
    return this.http.put<Users>(`${api_url}/changePassword?oldPassword=${oldPassword}`, obj);
  }

  // Get user

  getUserByUserName(userName: string): Observable<any> {
    return this.http.get<UserManage>(`${api_url}/userManage_by_userName?userName=${userName}`);
  }
  
}
