import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from '../Class/Location';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class LocationService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/location${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/locations`);
  }

  create(location: Location): Observable<any> {
    return this.http.post(`${apiUrl}/location`, location);
  }

  edit(location: Location): Observable<any> {
    return this.http.put(`${apiUrl}/location`, location);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/location/${id}`);
  }

  getByCity(city: string): Observable<any> {
    return this.http.get(`${apiUrl}/location_by_city?city=${city}`);
  }

}
