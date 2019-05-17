import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Users } from '../Class/Users';
import { JwtResponse } from '../Class/JwtResponse';
import {Observable, from} from 'rxjs';
import { SignIn } from '../Class/SignIn';

const apiUrl = 'http://localhost:8080/api';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) { }

  signUp(obj: Users): Observable<string> {
    return this.http.post<string>(`${apiUrl}/auth/signup`, obj, httpOptions);
  }

  signIn(obj: SignIn): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${apiUrl}/auth/signin`, obj, httpOptions);
  }

}
