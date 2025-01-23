import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = environment.api;

  constructor(private http: HttpClient) { }

  createOrder(): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'order', {});
  }

  getOrders(): Observable<any> {
    return this.http.get<any>(this.apiUrl + 'orders');
  }

}
