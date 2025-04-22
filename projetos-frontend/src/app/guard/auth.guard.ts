import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const router = inject(Router);
  const token = sessionStorage.getItem('authAccessToken');

  if(state.url === '/login'){
    if(token){
      router.navigate(['/land-page']);
      return false;
    }
    return true;
  }

  if(token){
    return true;
  }

  router.navigate(['/login']);
  return false;
};
