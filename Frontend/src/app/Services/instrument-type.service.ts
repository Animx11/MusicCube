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
    return this.http.get(`${apiUrl}/instrumenttype/${id}`);
  }

  getByInstrumentTypeName(instrumentTypeName: string): Observable<any> {
    return this.http.get(`${apiUrl}/instrumenttype/name/${instrumentTypeName}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/instrumenttype`);
  }

  create(instrumentType: InstrumentType): Observable<any> {
    return this.http.post(`${apiUrl}/admin/instrumenttype`, instrumentType);
  }

  edit(instrumentType: InstrumentType): Observable<any> {
    return this.http.put(`${apiUrl}/admin/instrumenttype`, instrumentType);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/instrumenttype/${id}`);
  }
}
