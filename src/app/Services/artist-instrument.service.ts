import { Injectable } from '@angular/core';
import { ArtistInstrument } from '../Class/ArtistInstrument';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class ArtistInstrumentService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistinstrument/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/artistinstrument`);
  }

  getByArtistId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistinstrument/artist/${id}`);
  }

  getByInstrumentId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artistinstrument/instrument/${id}`);
  }

  create(ai: ArtistInstrument): Observable<any> {
    return this.http.post(`${apiUrl}/admin/artistinstrument`, ai);
  }

  edit(ai: ArtistInstrument): Observable<any> {
    return this.http.put(`${apiUrl}/admin/artistinstrument`, ai);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/artistinstrument/${id}`);
  }
}
