import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { Category, Product } from '../../data/data.module';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(environment.api + 'products');
  }
  getCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(environment.api + 'category');
  }
  getProductsBySort(sortDirection: string = 'ASC', sortByName: boolean = true): Observable<Product[]> {
    const params = {
      sortDirection,
      sortByName: sortByName.toString()
    };
    return this.http.get<Product[]>(`${environment.api}product/sort`, { params });
  }
  filterProducts(categoryId?: number, minPrice?: number, maxPrice?: number): Observable<Product[]> {
    let params = new HttpParams();
    if (categoryId) {
      params = params.set('categoryId', categoryId.toString());
    }
    if (minPrice) {
      params = params.set('minPrice', minPrice.toString());
    }
    if (maxPrice) {
      params = params.set('maxPrice', maxPrice.toString());
    }
    return this.http.get<Product[]>(environment.api + 'product/filter', { params });
  }

  
}
