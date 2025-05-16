import { Component, OnInit, inject } from '@angular/core';
import { UsuarioService } from '../../service/usuario-service/usuario.service';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-gerenciar-users',
  imports: [CommonModule, FormsModule],
  templateUrl: './gerenciar-users.component.html',
  styleUrl: './gerenciar-users.component.css'
})
export class GerenciarUsersComponent implements OnInit{
  service = inject(UsuarioService);
  usuarios: any = [];
  semUsuarios: boolean = false;
  usuarioSelecionado: any;
  roleSelecionada: any;

  ngOnInit(): void {
    this.service.getUsuarios().subscribe((response) => {
      if(response != null){
        this.usuarios = response;
        console.log(this.usuarios);
      } else {
        this.semUsuarios = true;
        console.log("Lista de usuários vazia");
      }
    })
  }

  alterarCargo(){
    Swal.fire({
      title: "Tem certeza?",
      text: "Você está alterando o cargo de um usuário, verifique se o usuário selecionado esta correto assim como seu cargo, para evitar problemas.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Tenho, continuar."
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.atualizarCargo(this.usuarioSelecionado, this.roleSelecionada).subscribe((response) => {
          if (response != null){
            Swal.fire({
              title: response,
              text: "O usuário deverá realizar o login dele novamente para que seu novo cargo seja aplicado!",
              icon: "success"
            });
          } else {
            Swal.fire({
              title: "Erro interno, por favor tente novamente.",
              text: "Nos desculpe pelo incomodo.",
              icon: "error"
            });
          }
        })
      }
    });
  }
}
