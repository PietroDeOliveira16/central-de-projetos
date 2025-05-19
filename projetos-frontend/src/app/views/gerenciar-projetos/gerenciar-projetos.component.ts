import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProjetoService } from '../../service/projeto-service/projeto.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { UsuarioService } from '../../service/usuario-service/usuario.service';
import { elementAt } from 'rxjs';


@Component({
  selector: 'app-gerenciar-projetos',
  imports: [CommonModule, FormsModule, FontAwesomeModule],
  templateUrl: './gerenciar-projetos.component.html',
  styleUrl: './gerenciar-projetos.component.css'
})
export class GerenciarProjetosComponent implements OnInit{
  service = inject(ProjetoService)
  serviceUsuario = inject(UsuarioService)
  projetos: any = []
  semProjetos: boolean = false
  projetoSelecionado: any;
  plusIcon = faPlus;
  inputsProfessor = [{ value: '' }];
  inputsAluno = [{value: ''}];

  ngOnInit(): void {
    this.service.getProjetosInterface().subscribe((response) => {
      if(response != null){
        this.projetos = response;
        console.log(this.projetos);
      } else {
        this.semProjetos = true;
        console.log("Lista de projetos vazia");
      }
    })
  }

  editarProjeto(){
    var inputsCpfs: string[] = [];
    console.log(this.inputsProfessor);
    console.log(this.inputsAluno);
    this.inputsProfessor.forEach(element => {
      inputsCpfs.push('' + element.value);
    })
    this.inputsAluno.forEach(element => {
      inputsCpfs.push('' + element.value);
    });
    console.log(inputsCpfs);
    this.service.editarProjeto(this.projetoSelecionado, inputsCpfs).subscribe((response) => {

    })
  }

  adicionarInputProfessor(){
    this.inputsProfessor.push({ value: '' });
  }

  adicionarInputAluno(){
    this.inputsAluno.push({ value: '' });
  }

  trackByIndex(index: number, item: any): number{
    return index;
  }
}
