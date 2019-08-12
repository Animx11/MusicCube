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
    return this.http.get(`${apiUrl}/artist{id}?id=${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/artists`);
  }

  create(artist: Artist): Observable<any> {
    return this.http.post(`${apiUrl}/artist`, artist);
  }

  edit(artist: Artist): Observable<any> {
    return this.http.put(`${apiUrl}/artist`, artist);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/artist/${id}`);
  }
}
