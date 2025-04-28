import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';

@Injectable({
  providedIn: 'root'
})
export class LandPageService {
  readonly apiGetProjetosUrl = "http://localhost:8080/getProjetos";

  constructor(private http: HttpClient) { }

  getProjetos(){
    return this.http.get<Projeto[]>(this.apiGetProjetosUrl);
  }
}
