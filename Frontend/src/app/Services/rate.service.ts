import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


const apiUrl = api_url;


@Injectable({
  providedIn: 'root'
})
export class RateService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate?id=${id}`);
  }

  // Create rates

  createSongRate(userName: string, id: number, rate: number): Observable<any> {
    return this.http.post(`${apiUrl}/rate/song?userName=${userName}&songId=${id}&rate=${rate}`, null)
  }

  createAlbumRate(userName: string, id: number, rate: number): Observable<any> {
    return this.http.post(`${apiUrl}/rate/album?userName=${userName}&albumId=${id}&rate=${rate}`, null)
  }
  
  createBandRate(userName: string, id: number, rate: number): Observable<any> {
    return this.http.post(`${apiUrl}/rate/band?userName=${userName}&bandId=${id}&rate=${rate}`, null)
  }
  
  createArtistRate(userName: string, id: number, rate: number): Observable<any> {
    return this.http.post(`${apiUrl}/rate/artist?userName=${userName}&artistId=${id}&rate=${rate}`, null)
  }
  
  // *****

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/rate/${id}`);
  }

  edit(id: number, rate: number): Observable<any> {
    return this.http.put(`${apiUrl}/rate?id=${id}&rate=${rate}`, null);
  }

  // Gets

  getByUserNameAndSongId(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate/getByUserNameAndSongId?userName=${userName}&songId=${id}`);
  }

  getByUserNameAndAlbumId(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate/getByUserNameAndAlbumId?userName=${userName}&albumId=${id}`);
  }

  getByUserNameAndBandId(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate/getByUserNameAndBandId?userName=${userName}&bandId=${id}`);
  }

  getByUserNameAndArtistId(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/rate/getByUserNameAndArtistId?userName=${userName}&artistId=${id}`);
  }
}
