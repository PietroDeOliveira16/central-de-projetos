import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

export const adminGuard: CanActivateFn = (route, state) => {
  const cookieService = inject(CookieService);
  const cookie = cookieService.get("sessionCookie");
  const router = inject(Router);
  if(cookie == "ADMIN"){
    return true;
  } else {
    router.navigate(['/home'])
    return false;
  }
};
