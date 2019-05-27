import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Song } from "../Class/Song";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class SongService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/song{id}?id=${id}`);
  }

  getBySongName(songName: string): Observable<any> {
    return this.http.get(`${apiUrl}/songs{name}?songName=${songName}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songs`);
  }
  listPaging(pageNr: number, pageSize?: number): Observable<any> {
    return  pageSize ? this.http.get(`${apiUrl}/songs/${pageNr}`) : this.http.get(`${apiUrl}/songs/${pageNr}?size=${pageSize}`);
  }

  create(song: Song): Observable<any> {
    return this.http.post(`${apiUrl}/song`, song);
  }

  edit(song: Song): Observable<any> {
    return this.http.put(`${apiUrl}/song`, song);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/song/${id}`);
  }
}
