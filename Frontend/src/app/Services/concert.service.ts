import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Concert } from 'src/app/Class/Concert';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})

export class ConcertService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/concert/${id}`);
  }

  getByConcertName(name: string): Observable<any> {
    return this.http.get(`${apiUrl}/concert/concertName?concertName=${name}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/concert`);
  }

  create(concert: Concert): Observable<any> {
    return this.http.post(`${apiUrl}/admin/concert`, concert);
  }

  edit(concert: Concert): Observable<any> {
    return this.http.put(`${apiUrl}/admin/concert`, concert);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/concert/${id}`);
  }
}
