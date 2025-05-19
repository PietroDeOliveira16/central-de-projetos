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

  getProjetosInterface(){
    return this.http.get<any>(`${environment.projetoApiUrl}/admin/getProjetosSafe`, {withCredentials: true});
  }

  getDiasProjeto(codigoProjeto: String){
    return this.http.post(`${environment.projetoApiUrl}/getDiasProjeto`, codigoProjeto, { responseType: 'text' });
  }

  editarProjeto(codigoProjeto: string, cpfs: string[]) {
    return this.http.post(`${environment.projetoApiUrl}/admin/editarProjeto`, {codigoProjeto, cpfs}, {withCredentials: true, responseType: 'text'});
  }
}
