import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { News } from '../Class/news';
import { Observable } from 'rxjs';
import { api_url } from '../Utils/API_URL';

const apiUrl = api_url;

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  create(news: News): Observable<any> {
    return this.http.post(`${apiUrl}/admin/news`, news);
  }

  listPaging(page: number, size: number): Observable<any> {
    return this.http.get(`${apiUrl}/news/page/${page}?size=${size}`);
  }

  getNewsById(id: number): Observable<any> {
    return this.http.get(`${apiUrl}/news/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${apiUrl}/admin/news/${id}`);
  }

}
