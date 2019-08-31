import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentClass } from '../Class/CommentClass';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/${id}`);
  }

  create(comment: CommentClass, userName: string): Observable<any> {
    return this.http.post(`${apiUrl}/comment/${userName}`, comment);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/comment/${id}`);
  }

  edit(comment: CommentClass): Observable<any> {
    return this.http.put(`${apiUrl}/comment`, comment);
  }

  // Gets

  getBySongId(songId: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/song?songId=${songId}`);
  }

  getByAlbumId(albumId: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/album?albumId=${albumId}`);
  }

  getByBandId(bandId: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/band?bandId=${bandId}`);
  }

  getByArtistId(artistId: number): Observable<any> {
    return this.http.get(`${apiUrl}/comment/artist?artistId=${artistId}`);
  }

}
