import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Band } from '../Class/Band';

import { api_url } from '../Utils/API_URL';
import {Country} from '../Class/Country';
import {City} from '../Class/City';
import {Genre} from '../Class/Genre';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class BandService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}`);
  }
  getByBandName(term: string): Observable<any> {
    return this.http.get(`${apiUrl}/band/name/${term}/`);
  }
  getBandGenres(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}/genres`);
  }
  getBandAlbums(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${id}/albums`);
  }
  list(): Observable<any> {
    return this.http.get(`${apiUrl}/band`);
  }
  listPaging(pageNr: number, pageSize: number): Observable<any> {
    return this.http.get(`${apiUrl}/band/${pageNr}?size=${pageSize}`);
  }
  advancedSearch(city: City, country: Country, genre: Genre): Observable<any> {
    if (city && country) {
      return new Observable();
    }
    const areaStr = city ? `cityid=${city.getId()}` : country ? `countryid=${country.getId()}` : '';
    const genreStr = genre ? `genre=${genre.getGenreName()}` : '';
    const amp = (city || country) && genre ? '&' : '';

    return this.http.get(`${apiUrl}/band/advanced?${areaStr}${amp}${genreStr}`);
  }

  create(band: Band): Observable<any> {
    return this.http.post(`${apiUrl}/admin/band`, band);
  }

  edit(band: Band): Observable<any> {
    return this.http.put(`${apiUrl}/admin/band`, band);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/band/${id}`);
  }
}
