import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from '../Class/Artist';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artist/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/artist`);
  }

  getByName(name: string): Observable<any> {
    return this.http.get(`${apiUrl}/artist/name/${name}`);
  }

  create(artist: Artist): Observable<any> {
    return this.http.post(`${apiUrl}/admin/artist`, artist);
  }

  edit(artist: Artist): Observable<any> {
    return this.http.put(`${apiUrl}/admin/artist`, artist);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/artist/${id}`);
  }

}
