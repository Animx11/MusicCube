import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class FetchDataService {

  constructor(private http: HttpClient) { }

  fetch(offset: string, count: string): Observable<any> {
    return this.http.post(`${apiUrl}/admin/generate?offset=${offset}&iterations=${count}`, null);
  }
}
