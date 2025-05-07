import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjetoService {
  constructor(private http: HttpClient) { }

  getProjetos(){
    return this.http.get<Projeto[]>(`${environment.projetoApiUrl}/getProjetos`);
  }

  getDiasProjeto(codigoProjeto: String){
    return this.http.post(`${environment.projetoApiUrl}/getDiasProjeto`, codigoProjeto, { responseType: 'text' });
  }
}
