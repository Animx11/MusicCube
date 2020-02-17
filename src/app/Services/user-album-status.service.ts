import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { api_url } from '../Utils/API_URL';
import {Observable} from 'rxjs';
import {UserAlbumStatusDto} from '../DTOs/UserAlbumStatusDto';
const api = `${api_url}/user`;

@Injectable({
  providedIn: 'root'
})
export class UserAlbumStatusService {

  constructor(
    private http: HttpClient) { }
  getOwned(user: string): Observable<any> {
    return this.http.get(`${api}/owned-albums?user=${user}`);
  }
  getSought(user: string): Observable<any> {
    return this.http.get(`${api}/sought-albums?user=${user}`);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(`${api}/album-status/${id}`);
  }

  getByUserAndAlbum(userName: string, id: number) {
    return this.http.get(`${api}/album-status?user=${userName}&album=${id}`);
  }

  update(userName: string, id: number, owned: boolean, sought: boolean) {
    return this.http.put(
      `${api}/album-status`,
      new UserAlbumStatusDto(
        userName,
        id,
        owned,
        sought
      )
    );
  }
}
