import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { api_url } from '../Utils/API_URL';
import { ArtistActivity } from '../Class/ArtistActivity';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class ArtistActivityService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity/${id}`);
  }
  list(): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity`);
  }
  getByArtistId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity/artist/${id}`);
  }
  getByBandId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity/band/${id}`);
  }
  getByArtistIdIsActive(id: number, active: boolean): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity/artist/${id}?active=${active}`);
  }
  getByBandIdIsActive(id: number, active: boolean): Observable<any> {
    return this.http.get(`${apiUrl}/artistactivity/band/${id}?active=${active}`);
  }

  create(artistInBand: ArtistActivity): Observable<any> {
    return this.http.post(`${apiUrl}/admin/artistactivity`, artistInBand);
  }

  edit(artistInBand: ArtistActivity): Observable<any> {
    return this.http.put(`${apiUrl}/admin/artistactivity`, artistInBand);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/artistactivity/${id}`);
  }
}
