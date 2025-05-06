import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Projeto } from '../../model/projeto.type';

import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LandPageService {
  constructor(private http: HttpClient) { }

  getProjetos(){
    return this.http.get<Projeto[]>(`${environment.projetoApiUrl}/getProjetos`);
  }
}
