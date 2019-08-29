import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


const apiUrl = api_url;


@Injectable({
  providedIn: 'root'
})
export class RateService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate?id=${id}`);
  }

  create(userName: string, id: number, rate: number): Observable<any> {
    return this.http.post(`${apiUrl}/rate?userName=${userName}&songId=${id}&rate=${rate}`, null)
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/rate/${id}`);
  }

  edit(id: number, rate: number): Observable<any> {
    return this.http.put(`${apiUrl}/rate?id=${id}&rate=${rate}`, null);
  }

  getByUserNameAndSongId(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate/getByUserNameAndSongId?userName=${userName}&songId=${id}`);
  }
}
