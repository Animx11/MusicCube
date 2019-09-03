import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BandConcert } from '../Class/BandConcert';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class BandConcertService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/bandconcert/${id}`);
  }
  getByBandId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/bandconcert/band/${id}`);
  }
  getByConcertId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/bandconcert/concert/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/bandconcert`);
  }

  create(bandConcert: BandConcert): Observable<any> {
    return this.http.post(`${apiUrl}/admin/bandconcert`, bandConcert);
  }

  edit(bandConcert: BandConcert): Observable<any> {
    return this.http.put(`${apiUrl}/admin/bandconcert`, bandConcert);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/bandconcert/${id}`);
  }
}
