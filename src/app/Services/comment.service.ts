import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/${id}`);
  }

  create(comment: Comment): Observable<any> {
    return this.http.post(`${apiUrl}/comment`, comment);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/comment/${id}`);
  }

  edit(comment: Comment): Observable<any> {
    return this.http.put(`${apiUrl}/comment`, comment);
  }

  getBySongId(songId: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/${songId}`);
  }
}
