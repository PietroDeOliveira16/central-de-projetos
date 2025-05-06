import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  readonly apiUrlLogin = "http://localhost:8080/auth";

  constructor(private http: HttpClient) { }

  postLogar(username:String, password:String){
    return this.http.post<any>(this.apiUrlLogin + "/login", {username, password}, { withCredentials: true, responseType: 'text' as 'json' });
  }
}
