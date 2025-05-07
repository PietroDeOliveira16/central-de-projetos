import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CriarProjetoService {
  constructor(private http: HttpClient) { }

  postCriarProjeto(nome: String, descricao: String, codigo: String, dias:any){
    var projeto: Projeto = {
      nomeProjeto: nome,
      descricaoProjeto: descricao,
      codigoProjeto: codigo
    };

    const request = {
      projetoApi: projeto,
      dados: dias
    }

    return this.http.post<any>(`${environment.projetoApiUrl}/criarProjeto`, request, { withCredentials: true, responseType: 'text' as 'json' });
  }
}
