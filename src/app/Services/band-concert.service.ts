import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { BandConcert } from "../Class/BandConcert";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root",
})
export class BandConcertService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/bandConcerts${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/bandConcerts`);
  }

  create(bandConcert: BandConcert): Observable<any> {
    return this.http.post(`${apiUrl}/bandConcerts`, bandConcert);
  }

  edit(bandConcert: BandConcert): Observable<any> {
    return this.http.put(`${apiUrl}/bandConcerts`, bandConcert);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/bandConcerts/${id}`);
  }
}
