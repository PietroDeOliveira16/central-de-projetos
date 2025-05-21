import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';
import { environment } from '../../../environments/environment';
import { editarProjeto } from '../../model/editarProjeto.type';

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
    var request: editarProjeto = {
      codigoProjeto: codigoProjeto,
      cpfsMembrosDoProjeto: cpfs
    }
    console.log(request);
    return this.http.post(`${environment.projetoApiUrl}/admin/editarProjeto`, request, {withCredentials: true, responseType: 'text'});
  }

  getProjetosParticipando(){
    return this.http.get<any>(`${environment.projetoApiUrl}/getProjetosParticipando`, {withCredentials: true});
  }
}
