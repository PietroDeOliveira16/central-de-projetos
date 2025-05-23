import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

export interface Tarefa {
  id: number,
  nome: string,
  dataDeCriacao: Date,
  dataDeEntrega: Date,
  criadaPor: string,
  descricao: string,
  progresso: string
}

@Injectable({
  providedIn: 'root'
})
export class TarefasService {
  constructor(private http: HttpClient) { }

  getTarefasFromProjeto(id: Tarefa): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(`${environment.tarefaApiUrl}/getTarefas`, {withCredentials: true});
  }
}
