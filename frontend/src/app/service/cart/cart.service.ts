import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cart, Product, ProductsCart } from '../../data/data.module';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartUrl = environment.api + 'cart';

  constructor(private http: HttpClient) {}

  getCart(): Observable<Cart> {
    return this.http.get<Cart>(this.cartUrl);
  }
  
  changeProductAmount(productId: number, increase: boolean): Observable<Cart> {
    return this.http.post<Cart>(`${this.cartUrl}/changeProductAmount`, null, {
      params: {
        productId: productId.toString(),
        increase: increase.toString()
      }
    });
  }

  removeProduct(productId: number): Observable<Cart> {
    return this.http.delete<Cart>(`${this.cartUrl}`, {
      params: { productId: productId.toString() }
    });
  }

  getCartId(customerId: number): Observable<number> {
    const params = new HttpParams().set('customerId', customerId.toString());
    return this.http.get<number>(`${environment.api}getCartId`, { params });
  }

  addToCart(productId: number, quantity: number): Observable<any> {
    const url = `${this.cartUrl}`;
    let params = new HttpParams()
      .set('productId', productId.toString())
      .set('quantity', quantity.toString());

    return this.http.post(url, null, { params });
  }

}
