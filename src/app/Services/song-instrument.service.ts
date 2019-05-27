import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { SongInstrument } from "../Class/SongInstrument";
import { Song } from "../Class/Song";
import { Instrument } from "../Class/Instrument";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root"
})
export class SongInstrumentService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songInstrument{id}?id=${id}`);
  }
  getBySongId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songInstruments{songId}?id=${id}`);
  }
  getByInstrumentId(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/songInstrument{instrumentId}?id=${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/songInstruments`);
  }

  create(songInstrument: SongInstrument): Observable<any> {
    return this.http.post(`${apiUrl}/songInstrument`, songInstrument);
  }

  edit(songInstrument: SongInstrument): Observable<any> {
    return this.http.put(`${apiUrl}/songInstrument`, songInstrument);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/songInstrument/${id}`);
  }
}
