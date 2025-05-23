import { Component, Input, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjetoAbertoService } from '../../service/projeto-aberto-service/projeto-aberto.service';
import { Tarefa, TarefasService } from '../../service/tarefas-service/tarefas.service';
import { Observable } from 'rxjs';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-diario-de-bordo',
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './diario-de-bordo.component.html',
  styleUrl: './diario-de-bordo.component.css'
})
export class DiarioDeBordoComponent implements OnInit{
  constructor(private route: ActivatedRoute){}

  plusIcon = faPlus;

  projetoAbertoService = inject(ProjetoAbertoService);
  tarefasService = inject(TarefasService);

  projeto: any;
  tarefas: Observable<Tarefa[]> | undefined;

  ngOnInit(): void {
    this.projeto = this.projetoAbertoService.getProjetoAtual();
    if(this.projeto){
      console.log("Projeto mas no diario de bordo: ", this.projeto);
    } else {
      console.warn("Nenhum projeto carregado");
    }

    this.tarefas = this.tarefasService.getTarefasFromProjeto(this.projeto.id);
  }
}
