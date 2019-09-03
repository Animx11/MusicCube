import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Genre } from '../Class/Genre';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/genre/${id}`);
  }
  getByGenreName(term: string): Observable<any> {
    return this.http.get(`${apiUrl}/genre/name/${term}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/genre`);
  }

  create(genre: Genre): Observable<any> {
    return this.http.post(`${apiUrl}/admin/genre`, genre);
  }

  edit(genre: Genre): Observable<any> {
    return this.http.put(`${apiUrl}/admin/genre`, genre);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/genre/${id}`);
  }
}
