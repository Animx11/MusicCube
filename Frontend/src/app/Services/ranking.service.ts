import { Injectable } from '@angular/core';
import { api_url } from '../Utils/API_URL';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const apiUrl = `${api_url}/ranking`;

@Injectable({
  providedIn: 'root'
})
export class RankingService {

    constructor(private http: HttpClient) { }

    getTopRatedSongs(size: number): Observable<any> {
        return this.http.get(`${apiUrl}/song?size=${size}`);
    }
    getTopRatedAlbums(size: number): Observable<any> {
      return this.http.get(`${apiUrl}/album?size=${size}`);
    }
    getTopRatedBands(size: number): Observable<any> {
      return this.http.get(`${apiUrl}/band?size=${size}`);
    }
    getTopRatedArtists(size: number): Observable<any> {
      return this.http.get(`${apiUrl}/artist?size=${size}`);
    }

}
