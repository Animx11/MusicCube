import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ArtistInBand } from '../Class/ArtistInBand';

const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class ArtistInBandService {

  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any>{
    return this.http.get(`${apiUrl}/artistInBand${id}`);
  }

  list(): Observable<any>{
    return this.http.get(`${apiUrl}/artistInBands`);
  }

  create(artistInBand: ArtistInBand): Observable<any>{
    return this.http.post(`${apiUrl}/artistInBand`, artistInBand);
  }

  edit(artistInBand: ArtistInBand): Observable<any>{
    return this.http.put(`${apiUrl}/artistInBand`, artistInBand);
  }

  delete(id: number): Observable<any>{
    return this.http.delete(`${apiUrl}/artistInBand/${id}`)
  }

}
