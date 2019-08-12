import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {City} from '../Class/City';
import {HttpClient} from '@angular/common/http';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) { }
  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/city{id}?id=${id}`);
  }
  list(): Observable<any> {
    return this.http.get(`${apiUrl}/cities`);
  }

  create(city: City): Observable<any> {
    return this.http.post(`${apiUrl}/city`, city);
  }

  edit(city: City): Observable<any> {
    return this.http.put(`${apiUrl}/city`, city);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/city/${id}`);
  }
}
