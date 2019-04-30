import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Concert } from '../Class/Concert';
import { Observable } from 'rxjs';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class ConcertService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/concert${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/concerts`);
  }

  create(concert: Concert): Observable<any> {
    return this.http.post(`${apiUrl}/concert`, concert);
  }

  edit(concert: Concert): Observable<any> {
    return this.http.put(`${apiUrl}/concert`, concert);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/concert/${id}`);
  }

}
