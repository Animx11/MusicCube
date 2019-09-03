import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from '../Class/Artist';

import { api_url } from '../Utils/API_URL';
import {City} from '../Class/City';
import {Band} from '../Class/Band';
import {Instrument} from '../Class/Instrument';
import {Country} from '../Class/Country';
import {count} from 'rxjs/operators';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  constructor(private http: HttpClient) {}

  getById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/artist/${id}`);
  }

  list(): Observable<any> {
    return this.http.get(`${apiUrl}/artist`);
  }

  getByName(name: string): Observable<any> {
    return this.http.get(`${apiUrl}/artist/name/${name}/`);
  }

  advancedSearch(band: Band, city: City, country: Country, instrument: Instrument): Observable<any> {
    if (city && country) {
      return new Observable();
    }
    const bandId = band ? `band=${band.getId()}` : '';
    const amp1 = band && (city || country || instrument) ? '&' : '';
    const areaId = city ? `city=${city.getId()}` : country ? `country=${country.getId()}` : '';
    const amp2 = (country || city) && instrument ? '&' : '';
    const instrId = instrument ? `instrument=${instrument.getId()}` : '';

    const query = `${apiUrl}/artist/advanced?${bandId}${amp1}${areaId}${amp2}${instrId}`;

    return this.http.get(query);
  }

  create(artist: Artist): Observable<any> {
    return this.http.post(`${apiUrl}/admin/artist`, artist);
  }

  edit(artist: Artist): Observable<any> {
    return this.http.put(`${apiUrl}/admin/artist`, artist);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/artist/${id}`);
  }

}
