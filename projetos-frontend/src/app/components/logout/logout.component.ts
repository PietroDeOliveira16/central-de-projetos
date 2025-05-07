import { Component, inject } from '@angular/core';
import { AuthService } from '../../service/auth-service/auth.service';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit{
  service = inject(AuthService);

  constructor(private router: Router){}

  ngOnInit(): void {
    
  }
}
