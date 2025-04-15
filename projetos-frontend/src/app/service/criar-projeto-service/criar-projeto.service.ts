import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CriarProjetoService {
  readonly apiCriarUrl = "http://localhost:8080/criarProjeto";

  constructor(private http: HttpClient) { }

  postCriarProjeto(nome: String, descricao: String, codigo: String){
    var projeto: Projeto = {
      nomeProjeto: nome,
      descricaoProjeto: descricao,
      codigoProjeto: codigo
    };

    return this.http.post(this.apiCriarUrl, projeto, { responseType: 'text' });
  }
}
