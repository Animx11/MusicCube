import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Subgenre } from "../Class/Subgenre";

import { api_url } from "./API_URL";

const apiUrl = api_url;

@Injectable({
  providedIn: "root",
})
export class SubgenreService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/subgenre{id}?id=${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/subgenres`);
  }

  create(subgenre: Subgenre): Observable<any> {
    return this.http.post(`${apiUrl}/subgenre`, subgenre);
  }

  edit(subgenre: Subgenre): Observable<any> {
    return this.http.put(`${apiUrl}/subgenre`, subgenre);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/subgenre/${id}`);
  }
}
