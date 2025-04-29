import { Component, Signal, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CriarProjetoService } from '../../service/criar-projeto-service/criar-projeto.service';
import { NgClass } from '@angular/common';
import { CommonModule } from '@angular/common';
import * as Swal from 'sweetalert2';

@Component({
  selector: 'app-criar-projeto',
  imports: [FormsModule, NgClass, CommonModule],
  templateUrl: './criar-projeto.component.html',
  styleUrl: './criar-projeto.component.css'
})
export class CriarProjetoComponent {
  nome = "";
  descricao = "";
  codigo = "";
  dias: any = {
    dias:[
      {
        dia:"Segunda-feira",
        isChecked:false
      }, 
      {
        dia:"Terça-feira",
        isChecked:false
      }, 
      {
        dia:"Quarta-feira",
        isChecked:false
      },
      {
        dia:"Quinta-feira",
        isChecked:false
      },
      {
        dia:"Sexta-feira",
        isChecked:false
      }
    ], 
    horarioInicio:"14:00",
    horarioFim:"16:00"
  };
  readonly maxDescricao: number = 2000;
  textoLimiteDescricao = "0/" + String(this.maxDescricao);

  isQuaseMaxDesc = false;
  isMaxDesc = false;

  criarProjetoService = inject(CriarProjetoService);

  criarProjeto(){
    var diasFormatados: any[] = [];
    this.dias.dias.forEach((diaSelecionado:any) => {
      if(diaSelecionado.isChecked == true){
        diasFormatados.push(diaSelecionado.dia);
      }
    });
    diasFormatados.push(this.dias.horarioInicio);
    diasFormatados.push(this.dias.horarioFim);
    console.log("Dias formatados: ", diasFormatados);
    this.criarProjetoService.postCriarProjeto(this.nome, this.descricao, this.codigo, diasFormatados).subscribe((response: String) => {
      Swal.default.fire({title:response});
    });
  }

  checkLenght(event: Event){
    var textArea = (event.target as HTMLTextAreaElement);
    if(textArea.value.length >= this.maxDescricao){
      this.isMaxDesc = true;
    } else if (textArea.value.length >= ((this.maxDescricao / 2) + 500)){
      this.isQuaseMaxDesc = true;
    } else {
      this.isMaxDesc = false;
      this.isQuaseMaxDesc = false;
    }
    this.textoLimiteDescricao = textArea.value.length + "/" + String(this.maxDescricao);
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
