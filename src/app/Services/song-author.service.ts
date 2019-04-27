import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SongAuthor } from '../Class/SongAuthor';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class SongAuthorService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthor${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthors`);
  }

  create(songAuthor: SongAuthor): Observable<any> {
    return this.http.post(`${apiUrl}/songAuthor`, songAuthor);
  }

  edit(songAuthor: SongAuthor): Observable<any> {
    return this.http.put(`${apiUrl}/songAuthor`, songAuthor);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/songAuthor/${id}`);
  }

}
