import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { CriarProjetoComponent } from "./criar-projeto/criar-projeto.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, CriarProjetoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {}
