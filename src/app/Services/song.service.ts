import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Song } from '../Class/Song';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class SongService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/song/${id}`);
  }

  getBySongName(songName: string): Observable<any> {
    return this.http.get(`${apiUrl}/song/name/${songName}/`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/song`);
  }
  listPaging(pageNr: number, pageSize: number): Observable<any> {
    return this.http.get(`${apiUrl}/song/page/${pageNr}?size=${pageSize}`);
  }
  advanced(paramMap: Map<string, string>): Observable<any> {
    console.log(paramMap);
    let url = `${api_url}/search/song?`;
    if (paramMap.has('title')) {
      url += (`title=${paramMap.get('title')}`);
    }
    if (paramMap.has('notitle')) {
      url += (`notitle=${paramMap.get('notitle')}`);
    }
    return this.http.get(url);
  }

  create(song: Song): Observable<any> {
    return this.http.post(`${apiUrl}/admin/song`, song);
  }

  edit(song: Song): Observable<any> {
    return this.http.put(`${apiUrl}/admin/song`, song);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/song/${id}`);
  }
}
