import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subgenre } from '../Class/Subgenre';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class SubgenreService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/subgenre${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/subgenres`);
  }

  create(subgenre: Subgenre): Observable<any> {
    return this.http.post(`${apiUrl}/subgenre`, subgenre);
  }

  edit(subgenre: Subgenre): Observable<any> {
    return this.http.put(`${apiUrl}/subgenre`, subgenre);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/subgenre/${id}`);
  }

}