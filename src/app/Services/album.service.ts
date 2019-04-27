import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Album} from '../Class/Album'


const apiUrl = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})

export class AlbumService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any>{
      return this.http.get(`${apiUrl}/album${id}`);
  }

  create(obj: Album): Observable<any>{
      return this.http.post(`${apiUrl}/album`, obj);
  }

  list(): Observable<any>{
      return this.http.get(`${apiUrl}/albums`);
  }

  edit(obj: Album): Observable<any>{
      return this.http.put(`${apiUrl}/album`, obj);
  }

  delete(id: number): Observable<any>{
      return this.http.delete(`${apiUrl}/album/${id}`);
  }

}
