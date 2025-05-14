import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { inject } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';

export const loginGuard: CanActivateFn = (route, state) => {
  const cookieService = inject(CookieService);
  const router = inject(Router);
  const sessionCookie = cookieService.get('sessionCookie');
  if(sessionCookie != ''){
    console.log("Usuário já esta logado");
    router.navigate(['/home']);
    return false;
  } else {
    console.log("Usuário não logado");
    return true;
  }
};
