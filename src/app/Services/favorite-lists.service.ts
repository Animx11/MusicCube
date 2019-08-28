import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import { Observable } from 'rxjs';
import { Song } from '../Class/Song';
import { HttpClient } from '@angular/common/http';
import { Album } from '../Class/Album';
import { Band } from '../Class/Band';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})

export class FavoriteListsService {

  constructor(private http: HttpClient) { }

  // Get Favorite Lists

  getUserFavoriteSongsByUserName(userName: string): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/song?userName=${userName}`);
  }

  getUserFavoriteAlbumsByUserName(userName: string): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/album?userName=${userName}`);
  }

  getUserFavoriteBandsByUserName(userName: string): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/band?userName=${userName}`);
  }

  // Add and delete from favorite list

  addSongToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/song/add?userName=${userName}&id=${id}`, null);
  }

  deleteSongToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/song/delete?userName=${userName}&id=${id}`, null);
  }

  addAlbumToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/album/add?userName=${userName}&id=${id}`, null);
  }

  deleteAlbumToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/album/delete?userName=${userName}&id=${id}`, null);
  }
  
  addBandToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/band/add?userName=${userName}&id=${id}`, null);
  }

  deleteBandToFavorites(userName: string, id: number): Observable<any> {
    return this.http.patch(`${apiUrl}/userFavorites/band/delete?userName=${userName}&id=${id}`, null);
  }

  // Check if exist in user favorite list

  existSongInUserFavorites(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/song/exist?userName=${userName}&id=${id}`);
  }

  existAlbumInUserFavorites(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/album/exist?userName=${userName}&id=${id}`);
  }

  existBandInUserFavorites(userName: string, id: number): Observable<any> {
    return this.http.get(`${apiUrl}/userFavorites/band/exist?userName=${userName}&id=${id}`);
  }

}
