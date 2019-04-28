import { Injectable } from '@angular/core';
import { Instrument } from '../Class/Instrument';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class InstrumentService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/instrument${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/instruments`);
  }

  create(instrument: Instrument): Observable<any> {
    return this.http.post(`${apiUrl}/instrument`, instrument);
  }

  edit(instrument: Instrument): Observable<any> {
    return this.http.put(`${apiUrl}/instrument`, instrument);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/instrument/${id}`);
  }

}
