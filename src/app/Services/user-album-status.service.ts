import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { api_url } from '../Utils/API_URL';
import {Observable} from 'rxjs';
import {UserAlbumStatus} from '../Class/UserAlbumStatus';
const api = `${api_url}/user`;

@Injectable({
  providedIn: 'root'
})
export class UserAlbumStatusService {

  constructor(private http: HttpClient) { }
  getOwned(user: string): Observable<any> {
    return this.http.get(`${api}/owned-albums?user=${user}`);
  }
  getSought(user: string): Observable<any> {
    return this.http.get(`${api}/sought-albums?user=${user}`);
  }
  create(status: UserAlbumStatus): Observable<any> {
    return this.http.post(`${api}/album-status`, status);
  }
  edit(status: UserAlbumStatus): Observable<any> {
    return this.http.put(`${api}/album-status`, status);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(`${api}/album-status/${id}`);
  }
}
