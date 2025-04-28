import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../service/login-service/login.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = "";
  password = "";

  service = inject(LoginService);

  logar(){
    

    /*this.service.postLogar(this.username, this.password).subscribe((response) => {
      console.log(response.accessToken);
    });*/
  }
}