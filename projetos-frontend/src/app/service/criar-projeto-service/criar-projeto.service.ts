import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CriarProjetoService {
  readonly apiCriarUrl = "http://localhost:8080/projeto";

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

    return this.http.post<any>(this.apiCriarUrl + "/criarProjeto", request, { withCredentials: true, responseType: 'text' as 'json' });
  }
}
