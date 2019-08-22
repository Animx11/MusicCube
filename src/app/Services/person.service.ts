import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Person } from 'src/app/Class/Person';
import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  constructor(private http: HttpClient) {}
  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/person{id}?id=${id}`);
  }
  getByLastName(term: string): Observable<any> {
    if (term === '') { return new Observable<any>(); } else { return this.http.get(`${apiUrl}/persons{lastname}?lastName=${term}`); }
  }
}
