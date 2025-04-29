import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';

@Injectable({
  providedIn: 'root'
})
export class ProjetoService {
  readonly apiGetProjetosUrl = "http://localhost:8080/getProjetos";
  readonly apiGetDiasProjetoUrl = "http://localhost:8080/getDiasProjeto";

  constructor(private http: HttpClient) { }

  getProjetos(){
    return this.http.get<Projeto[]>(this.apiGetProjetosUrl);
  }

  getDiasProjeto(codigoProjeto: String){
    return this.http.post(this.apiGetDiasProjetoUrl, codigoProjeto, { responseType: 'text' });
  }
}
