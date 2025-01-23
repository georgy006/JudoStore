import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { Customer, CustomerInfo, Order } from '../../data/data.module';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = environment.api;

  constructor(private http: HttpClient) { }

  getCustomer(): Observable<CustomerInfo> {
    return this.http.get<CustomerInfo>(this.apiUrl + 'customer');
  }
}
