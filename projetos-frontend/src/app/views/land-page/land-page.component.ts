import { Component, OnInit, inject } from '@angular/core';
import { LandPageService } from '../../service/land-page-service/land-page.service';
import { Projeto } from '../../model/projeto.type';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-land-page',
  imports: [CommonModule],
  templateUrl: './land-page.component.html',
  styleUrl: './land-page.component.css'
})
export class LandPageComponent implements OnInit{
  constructor(private router: Router){}

  service = inject(LandPageService);
  projetos: Projeto[] = [];
  projetoVazio: boolean = false;

  ngOnInit(): void {
    this.service.getProjetos().subscribe((response) => {
      console.log(response);
      if(response.length <= 0){
        this.projetoVazio = true;
      } else {
        this.projetos = response;
        this.projetoVazio = false;
      }
    },
    (erro) => {
      console.error('Erro ao obter os projetos da api: ', erro);
      this.projetoVazio = true;
    });
  }

  saibaMais(codProjeto: String){
    console.log("cod do projeto: ", codProjeto);
    this.projetos.forEach((projeto) => {
      if(projeto.codigoProjeto === codProjeto){
        sessionStorage.setItem("projetoSaibaMais", JSON.stringify(projeto));
        this.router.navigate(['/saiba-mais']);
      }
    })
  }
}
