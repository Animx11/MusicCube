import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Localization } from 'src/app/Class/Localization';
import { api_url } from './API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class LocalizationService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/localization{id}?id=${id}`);
  }

  getByCountry(country: string): Observable<any> {
    if (country === '') { return new Observable<any>(); } else {
      return this.http.get(
        `${apiUrl}/localization{country}?country=${country}`
      );
    }
  }
  getByCity(city: string): Observable<any> {
    return this.http.get(`${apiUrl}/localization{city}?city=${city}`);
  }
  getByAddress(address: string): Observable<any> {
    return this.http.get(`${apiUrl}/localization{address}?address=${address}`);
  }
  getByAnything(input: string): Observable<any> {
    if (input === '') { return new Observable<any>(); } else {
      return this.http.get(`${apiUrl}/localization{anything}?input=${input}`);
    }
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/localizations`);
  }

  create(localization: Localization): Observable<any> {
    return this.http.post(`${apiUrl}/localization`, localization);
  }

  edit(localization: Localization): Observable<any> {
    return this.http.put(`${apiUrl}/localization`, localization);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/localization/${id}`);
  }
}
