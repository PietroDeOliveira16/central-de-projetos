import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  readonly apiLoginUrl = "http://localhost:8080/auth/authenticate";

  constructor(private http: HttpClient) { }

  postLogar(username:String, password:String){
    return this.http.post<any>(this.apiLoginUrl, { username, password })
    .pipe(
      tap(response => {
        sessionStorage.setItem('authToken', response.token);
      })
    );
  }
}
