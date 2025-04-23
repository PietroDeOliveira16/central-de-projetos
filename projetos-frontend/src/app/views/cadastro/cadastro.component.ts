import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CadastroService } from '../../service/cadastro-service/cadastro.service';
import * as Swal from 'sweetalert2';

@Component({
  selector: 'app-cadastro',
  imports: [FormsModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {
  username = "";
  senha = "";
  confirmaSenha = "";
  nome = "";
  telefone = "";
  email = "";
  cpf = "";

  service = inject(CadastroService);

  cadastrar(){
    this.service.postCadastrarUsuario(this.username, this.senha, this.nome, this.telefone, this.email, this.cpf).subscribe(
      (response) => {
        Swal.default.fire({title:response});
    });
  }
}