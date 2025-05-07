import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

export const jwtGuard: CanActivateFn = (route, state) => {
  const cookieService = inject(CookieService);
  const router = inject(Router);
  const sessionCookie = cookieService.get('sessionCookie');
  if(sessionCookie != ''){
    console.log(sessionCookie);
    return true;
  } else {
    console.log("Usuário não logado");
    router.navigate(['/login']);
    return false;
  }
};
