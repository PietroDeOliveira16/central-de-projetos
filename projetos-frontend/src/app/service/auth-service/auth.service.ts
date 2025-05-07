import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  postLogar(username:String, password:String){
    return this.http.post<any>(`${environment.authApiUrl}/login`, {username, password}, { withCredentials: true, responseType: 'text' as 'json' });
  }
}
