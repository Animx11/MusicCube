import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Band } from '../Class/Band';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class BandService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}`);
  }
  getByBandName(term: string): Observable<any> {
    return this.http.get(`${apiUrl}/band/name/${term}/`);
  }
  getBandGenres(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}/genres`);
  }
  getBandAlbums(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}/albums`);
  }
  list(): Observable<any> {
    return this.http.get(`${apiUrl}/band`);
  }
  listPaging(pageNr: number, pageSize: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${pageNr}?size=${pageSize}`);
  }

  create(band: Band): Observable<any> {
    return this.http.post(`${apiUrl}/admin/band`, band);
  }

  edit(band: Band): Observable<any> {
    return this.http.put(`${apiUrl}/admin/band`, band);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/band/${id}`);
  }
}
