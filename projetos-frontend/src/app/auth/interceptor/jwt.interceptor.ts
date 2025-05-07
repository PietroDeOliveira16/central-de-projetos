import { HttpEvent, HttpHandler, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { AuthService } from '../../service/auth-service/auth.service';
import { Observable } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next): Observable<HttpEvent<unknown>> => {
  return next(req);
};
