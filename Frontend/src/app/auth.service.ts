import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,catchError,throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { email, password }, { responseType: 'text' });
  }

  register(name: string, email: string, password: string): Observable<string> {
    const body = { name, email, password };
    return this.http.post<string>('http://localhost:8080/auth/register', body, {
      responseType: 'text' as 'json',
    });
  }

  updateAccount(token: string, updates: { name?: string; email?: string; password?: string }): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.put(`${this.baseUrl}/update`, updates, { headers, responseType: 'text' })
      .pipe(
        catchError((error) => {
          console.error('Update Account Error:', error);
          return throwError(() => error);
        })
      );
  }

  deleteAccount(token: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.delete(`${this.baseUrl}/delete`, { headers, responseType: 'text' })
      .pipe(
        catchError((error) => {
          console.error('Delete Account Error:', error);
          return throwError(() => error);
        })
      );
  }
}
