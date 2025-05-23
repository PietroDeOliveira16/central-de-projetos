import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { ProjetoService } from '../projeto-service/projeto.service';

export interface ProjetoAberto {
  id: number,
  codProjeto: string,
  nome: string,
  descricao: string,
  dataCriacao: Date,
  dataTermino: Date | null
}

@Injectable({
  providedIn: 'root'
})
export class ProjetoAbertoService {
  private projetoSubject = new BehaviorSubject<ProjetoAberto | null>(null);
  public projeto$ = this.projetoSubject.asObservable();

  constructor(private http: HttpClient) {
    const projetoSalvo = sessionStorage.getItem('projetoAberto');
    if (projetoSalvo) {
      this.projetoSubject.next(JSON.parse(projetoSalvo));
    }
  }

  projetoService = inject(ProjetoService);

  getProjetoAtual(): ProjetoAberto | null {
    return this.projetoSubject.value;
  }

  carregarProjeto(cod: string): Observable<ProjetoAberto> {
    const atual = this.getProjetoAtual();
    if (atual && atual.codProjeto === cod) {
      return of(atual); // já está carregado
    }

    return this.projetoService.acharProjeto(cod).pipe(
      tap((projeto: any) => {
        this.projetoSubject.next(projeto);
        sessionStorage.setItem('projetoAberto', JSON.stringify(projeto));
      })
    );
  }

  limparProjeto() {
    this.projetoSubject.next(null);
    sessionStorage.removeItem('projetoAberto');
  }
}
