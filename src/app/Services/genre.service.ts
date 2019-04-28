import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Genre } from '../Class/Genre';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class GenreService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/genre${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/genres`);
  }

  create(genre: Genre): Observable<any> {
    return this.http.post(`${apiUrl}/genre`, genre);
  }

  edit(genre: Genre): Observable<any> {
    return this.http.put(`${apiUrl}/genre`, genre);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/genre/${id}`);
  }

}
