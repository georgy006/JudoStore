// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router) { }

  login(login: string, password: string): Observable<any> {
    const url = `${this.baseUrl}/customer/login`;
    const body = { login, password };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(url, body, { headers }).pipe(
      map((response: any) => {
        if (response && response.token) {
          localStorage.setItem('token', response.token);
        }
        return response;
      })
    );
  }

  register(name: string, email: string, password: string, city: string, phoneNumber: string): Observable<any> {
    const url = `${this.baseUrl}/customer/register`;
    const body = { name, email, password, city, phoneNumber };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(url, body, { headers });
  }

  logout() {
    const url = `${this.baseUrl}/customers/logout`;
    const body = { };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log("отправка запроса логаут");
    return this.http.post(url, body, { headers }).pipe(
      map((response: any) => {
        console.warn(response)
        localStorage.removeItem('token');
        this.router.navigate(['/']);
        return response;
      })
    );
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}
