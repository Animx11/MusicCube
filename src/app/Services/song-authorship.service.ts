import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { SongAuthorship } from "../Class/SongAuthorship";
import { Person } from "src/app/Class/Person";
import { Song } from "src/app/Class/Song";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root"
})
export class SongAuthorshipService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthorship{id}?id=${id}`);
  }
  getBySongId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthorships{songId}?id=${id}`);
  }
  getByAuthorId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthorships{authorId}?id=${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songAuthorships`);
  }

  create(songAuthorship: SongAuthorship): Observable<any> {
    return this.http.post(`${apiUrl}/songAuthorship`, songAuthorship);
  }

  edit(songAuthorship: SongAuthorship): Observable<any> {
    return this.http.put(`${apiUrl}/songAuthorship`, songAuthorship);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/songAuthorship/${id}`);
  }
}
