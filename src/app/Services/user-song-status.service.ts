import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserSongStatus} from '../Class/UserSongStatus';
const api = `${api_url}/user`;

@Injectable({
  providedIn: 'root'
})
export class UserSongStatusService {

  constructor(private http: HttpClient) { }
  getListened(user: string): Observable<any> {
    return this.http.get(`${api}/listened-songs?user=${user}`);
  }
  getToListen(user: string): Observable<any> {
    return this.http.get(`${api}/songs-to-listen?user=${user}`);
  }
  create(status: UserSongStatus): Observable<any> {
    return this.http.post(`${api}/song-status`, status);
  }
  edit(status: UserSongStatus): Observable<any> {
    return this.http.put(`${api}/song-status`, status);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(`${api}/song-status/${id}`);
  }
}
