import { Component, Signal, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CriarProjetoService } from '../service/criar-projeto-service/criar-projeto.service';
import * as Swal from 'sweetalert2';

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
      Swal.default.fire({title:response});
    });
  }

  checkLenght(){
    console.log('checando input');
  }

  onFocusOut(event: FocusEvent){
    const elemento = event.target as HTMLInputElement | HTMLTextAreaElement;
    if (elemento.value === ''){
      elemento.classList.add('is-invalid');
    } else {
      elemento.classList.remove('is-invalid');
    }
  }
}
