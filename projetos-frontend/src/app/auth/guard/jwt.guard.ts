import { Inject, inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { AuthService } from '../../service/auth-service/auth.service';

export const jwtGuard: CanActivateFn = (route, state) => {
  return true;
};
