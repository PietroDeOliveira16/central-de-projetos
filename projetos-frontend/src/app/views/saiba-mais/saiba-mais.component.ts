import { Component, inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProjetoService } from '../../service/projeto-service/projeto.service';

@Component({
  selector: 'app-saiba-mais',
  imports: [CommonModule],
  templateUrl: './saiba-mais.component.html',
  styleUrl: './saiba-mais.component.css'
})
export class SaibaMaisComponent implements OnInit{
  constructor(private router: Router){}
  projetoSelecionadoJson: any;
  projetoSelecionado: any;
  diasProjetoJson: any;
  dias: String[] = [];
  horas: String[] = [];

  service = inject(ProjetoService);

  ngOnInit(): void {
    this.projetoSelecionadoJson = sessionStorage.getItem("projetoSaibaMais");
    if(!this.projetoSelecionadoJson){
      this.router.navigate(["/land-page"]);
    } else {
      this.projetoSelecionado = JSON.parse(this.projetoSelecionadoJson);
      this.service.getDiasProjeto(this.projetoSelecionado.codigoProjeto).subscribe((response) => {
        const partes = response
          .split(/[;\/]/)
          .map(p => p.trim())
          .filter(p => p);
        
        partes.forEach(parte => {
          const primeiroCaractere = parte.charAt(0);

          if(/[a-zA-Z]/.test(primeiroCaractere)){
            this.dias.push(parte);
          } else {
            this.horas.push(parte);
          }
        })
        console.log("Dias: ", this.dias);
        console.log("Horas: ", this.horas);
      });
    }
  }




}
