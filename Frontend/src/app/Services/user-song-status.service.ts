import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserSongStatusDto} from '../DTOs/UserSongStatusDto';
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
  delete(id: number): Observable<any> {
    return this.http.delete(`${api}/song-status/${id}`);
  }
  getByUserAndSong(userName: string, id: number) {
    return this.http.get(`${api}/song-status?user=${userName}&song=${id}`);
  }

  update(userName: string, id: number, listened: boolean, toListen: boolean) {
    return this.http.put(
      `${api}/song-status`,
      new UserSongStatusDto(
        userName,
        id,
        listened,
        toListen
      )
    );
  }

}
