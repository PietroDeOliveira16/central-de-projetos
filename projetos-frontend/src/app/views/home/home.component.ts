import { Component, OnInit, inject } from '@angular/core';
import { ProjetoService } from '../../service/projeto-service/projeto.service';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  projetoService = inject(ProjetoService)
  projetos: any[] = [];
  projetosVazio: boolean = false

  ngOnInit(): void {
    this.projetoService.getProjetosParticipando().subscribe((response:[]) => {
      console.log(response);
      if(response.length > 0){
        this.projetos = response; 
      } else {
        this.projetosVazio = true;
      }
    })
  }
}
