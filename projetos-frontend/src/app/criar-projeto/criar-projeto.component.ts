import { Component, Signal, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CriarProjetoService } from '../service/criar-projeto.service';

@Component({
  selector: 'app-criar-projeto',
  imports: [FormsModule],
  templateUrl: './criar-projeto.component.html',
  styleUrl: './criar-projeto.component.css'
})
export class CriarProjetoComponent {
  nome = "";
  descricao = "";
  codigo = "";

  criarProjetoService = inject(CriarProjetoService);

  criarProjeto(){
    this.criarProjetoService.postCriarProjeto(this.nome, this.descricao, this.codigo).subscribe((response: String) => {
      console.log(response);
    });
  }

  checkLenght(){
    // esse código vai checar e garantir que a descrição tenha até no máximo 2000 caracteres
  }
}
