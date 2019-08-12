import { Injectable } from "@angular/core";
import { Instrument } from "../Class/Instrument";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

import { api_url } from "../Utils/API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root"
})
export class InstrumentService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/instrument{id}?id=${id}`);
  }
  getByInstrumentName(term: string): Observable<any> {
    return this.http.get(`${apiUrl}/instruments{name}?instrumentName=${term}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/instruments`);
  }

  create(instrument: Instrument): Observable<any> {
    return this.http.post(`${apiUrl}/instrument`, instrument);
  }

  edit(instrument: Instrument): Observable<any> {
    return this.http.put(`${apiUrl}/instrument`, instrument);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/instrument/${id}`);
  }
}
