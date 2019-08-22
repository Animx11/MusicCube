import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {InstrumentType} from '../Class/InstrumentType';
import {HttpClient} from '@angular/common/http';

import { api_url } from '../Utils/API_URL';
const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class InstrumentTypeService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/instrumentType{id}?id=${id}`);
  }

  getByInstrumentTypeName(instrumentTypeName: string): Observable<any> {
    return this.http.get(`${apiUrl}/instrumentType{name}?instrumentTypeName=${instrumentTypeName}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/instrumentTypes`);
  }

  create(instrumentType: InstrumentType): Observable<any> {
    return this.http.post(`${apiUrl}/instrumentType`, instrumentType);
  }

  edit(instrumentType: InstrumentType): Observable<any> {
    return this.http.put(`${apiUrl}/instrumentType`, instrumentType);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/instrumentType/${id}`);
  }
}
