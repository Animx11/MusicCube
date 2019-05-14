import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Band } from "../Class/Band";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root",
})
export class BandService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/bands`);
  }

  create(band: Band): Observable<any> {
    return this.http.post(`${apiUrl}/band`, band);
  }

  edit(band: Band): Observable<any> {
    return this.http.put(`${apiUrl}/band`, band);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/band/${id}`);
  }
}
