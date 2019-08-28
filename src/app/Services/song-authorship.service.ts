import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SongAuthorship } from '../Class/SongAuthorship';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class SongAuthorshipService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songauthorship/${id}`);
  }
  getBySongId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songauthorship/song/${id}`);
  }
  getByAuthorId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songauthorsip/author/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songauthorsip`);
  }

  create(songAuthorship: SongAuthorship): Observable<any> {
    return this.http.post(`${apiUrl}/admin/songauthorsip`, songAuthorship);
  }

  edit(songAuthorship: SongAuthorship): Observable<any> {
    return this.http.put(`${apiUrl}/admin/songauthorsip`, songAuthorship);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/songauthorsip/${id}`);
  }
}
