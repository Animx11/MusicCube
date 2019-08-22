import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InstrumentTypeService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/instrumentType{id}?id=${id}`);
  }

  getByInstrumentTypeName(instrumentTypeName: string): Observable<any> {
    return this.http.get(`${apiUrl}/artists{name}?instrumentTypeName=${instrumentTypeName}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/instrumentTypes`);
  }

  create(city: City): Observable<any> {
    return this.http.post(`${apiUrl}/instrumentType`, city);
  }

  edit(city: City): Observable<any> {
    return this.http.put(`${apiUrl}/instrumentType`, city);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/instrumentType/${id}`);
  }
}
