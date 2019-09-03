import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SongInstrument } from '../Class/SongInstrument';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class SongInstrumentService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songinstrument/${id}`);
  }
  getBySongId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songinstrument/song/${id}`);
  }
  getByInstrumentId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songinstrument/instrument/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songinstrument`);
  }

  create(songInstrument: SongInstrument): Observable<any> {
    return this.http.post(`${apiUrl}/admin/songinstrument`, songInstrument);
  }

  edit(songInstrument: SongInstrument): Observable<any> {
    return this.http.put(`${apiUrl}/admin/songinstrument`, songInstrument);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/songinstrument/${id}`);
  }
}
