import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cadastro } from '../../model/cadastro.type';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {
  readonly apiCadastroUrl = "http://localhost:8080/auth";

  constructor(private http: HttpClient) { }

  postCadastrarUsuario(username:String, senha:String, nome:String, telefone:String, email:String, cpf:String){
    var cadastro: Cadastro = {
      username: username,
      senha: senha,
      nome: nome,
      telefone: telefone,
      email: email,
      cpf: cpf
    };
    return this.http.post(this.apiCadastroUrl + "/cadastro", cadastro, { responseType: 'text' });
  }
}