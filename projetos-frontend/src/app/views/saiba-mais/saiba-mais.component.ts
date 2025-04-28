import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

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

  ngOnInit(): void {
    this.projetoSelecionadoJson = sessionStorage.getItem("projetoSaibaMais");
    if(!this.projetoSelecionadoJson){
      this.router.navigate(["/land-page"]);
    } else {
      this.projetoSelecionado = JSON.parse(this.projetoSelecionadoJson);
    }
  }




}
