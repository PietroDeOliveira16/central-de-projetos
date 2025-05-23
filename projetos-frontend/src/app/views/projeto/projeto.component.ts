import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjetoService } from '../../service/projeto-service/projeto.service';
import { RouterOutlet } from '@angular/router';
import { ProjetoAbertoService } from '../../service/projeto-aberto-service/projeto-aberto.service';

@Component({
  selector: 'app-projeto',
  imports: [CommonModule, FormsModule, RouterOutlet],
  templateUrl: './projeto.component.html',
  styleUrl: './projeto.component.css'
})
export class ProjetoComponent implements OnInit, OnDestroy{
  constructor(private route: ActivatedRoute, private router: Router){}

  projetoAbertoService = inject(ProjetoAbertoService);

  projeto: any

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const codigoProjeto = params['cod_projeto'];
      if(codigoProjeto){
        this.projetoAbertoService.carregarProjeto(codigoProjeto).subscribe(response => {
          this.projeto = response;
        });
      }
    })
  }

  ngOnDestroy(): void {
    this.projetoAbertoService.limparProjeto();
  }

  routeTo(route: string){
    this.router.navigate(['/projeto', route]);
  }
}
