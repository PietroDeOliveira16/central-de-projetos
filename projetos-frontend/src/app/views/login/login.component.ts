import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth-service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private router: Router) {}

  username = "";
  password = "";

  service = inject(AuthService);

  logar(){
    this.service.postLogar(this.username, this.password).subscribe((response) => {
      console.log(response);
    });
  }
}