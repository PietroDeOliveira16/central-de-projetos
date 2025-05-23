import { Component, inject } from '@angular/core';
import { ProjetoAbertoService } from '../../service/projeto-aberto-service/projeto-aberto.service';

@Component({
  selector: 'app-arquivos',
  imports: [],
  templateUrl: './arquivos.component.html',
  styleUrl: './arquivos.component.css'
})
export class ArquivosComponent {
  projetoAbertoService = inject(ProjetoAbertoService);

  ngOnInit(): void {
    const projeto = this.projetoAbertoService.getProjetoAtual();
    if(projeto){
      console.log("Projeto mas no diario de bordo: ", projeto);
    } else {
      console.warn("Nenhum projeto carregado");
    }
  }
}
