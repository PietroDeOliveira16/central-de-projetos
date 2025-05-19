import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  constructor(private http: HttpClient) { }

  getUsuarios(){
    return this.http.get<any>(`${environment.authApiUrl}/getUsuarios`, {});
  }

  atualizarCargo(id: any, role: any){
    return this.http.post<any>(`${environment.authApiUrl}/admin/atualizarRole`, {id, role}, { withCredentials: true, responseType: 'text' as 'json'})
  }
}
