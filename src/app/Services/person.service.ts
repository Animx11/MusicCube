import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { api_url } from '../Utils/API_URL';
import {Person} from '../Class/Person';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  constructor(private http: HttpClient) {}
  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/person/${id}`);
  }
  getByLastName(term: string): Observable<any> {
    if (term === '') { return new Observable<any>(); } else { return this.http.get(`${apiUrl}/person/lastname/${term}`); }
  }

  getByAnything(input: string): Observable<any> {
    return this.http.get(`${apiUrl}/person/anything?input=${input}`);
  }

  create(person: Person): Observable<any> {
    return this.http.post(`${apiUrl}/admin/person`, person);
  }

  edit(person: Person): Observable<any> {
    return this.http.put(`${apiUrl}/admin/person`, person);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/person/${id}`);
  }
}
