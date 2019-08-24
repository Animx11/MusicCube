import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Album } from '../Class/Album';
import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/album/${id}`);
  }
  getByAlbumName(name: string): Observable<any> {
    if (name === '') { return new Observable<any>(); } else { return this.http.get(`${apiUrl}/album/name/${name}`); }
  }
  getAlbumBands(albumId: number): Observable<any> {
    return this.http.get(`${apiUrl}/album/${albumId}/bands`);
  }
  getAlbumSongs(albumId: number): Observable<any> {
    return this.http.get(`${apiUrl}/album/${albumId}/songs`);
  }
  listPaging(page: number, size: number): Observable<any> {
    return this.http.get(`${apiUrl}/album/page/${page}?size=${size}`);
  }
  list(): Observable<any> {
    return this.http.get(`${apiUrl}/album`);
  }

  create(obj: Album): Observable<any> {
    return this.http.post(`${apiUrl}/admin/album`, obj);
  }


  edit(obj: Album): Observable<any> {
    return this.http.put(`${apiUrl}/admin/album`, obj);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/album/${id}`);
  }
}
