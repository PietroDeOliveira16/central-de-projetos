import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import Swal from 'sweetalert2';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { inject } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  cookieService = inject(CookieService);

  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  public isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();

  private roleSubject = new BehaviorSubject<string>('');
  public role$: Observable<string> = this.roleSubject.asObservable();


  constructor(private http: HttpClient, private router: Router) { 
    const session = this.getSession();
    const sessionRoleString = this.getSessionRole();
    this.isLoggedInSubject.next(!!session);
    this.roleSubject.next(sessionRoleString);
  }

  private getSession(): boolean{
    if(this.cookieService.get('sessionCookie')){
      return true;
    } else {
      return false;
    }
  }

  getSessionRole(): string{
    return this.cookieService.get('sessionCookie');
  }

  postLogar(username:String, password:String){
    return this.http.post<any>(`${environment.authApiUrl}/login`, {username, password}, { withCredentials: true, responseType: 'text' as 'json' });
  }

  postLogout(): Observable<void>{
    return this.http.post<void>(`${environment.authApiUrl}/logout`, {}, {withCredentials: true});
  }

  logout(): void{
    if(this.cookieService.get('sessionCookie')){
      Swal.fire({
        title: "Você está prestes e encerrar sua sessão no site",
        text: "Você será redirecionado para a página de login e tudo que estiver fazendo agora sera perdido!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Entendi, continuar logout"
      }).then((result) => {
        if (result.isConfirmed) {
          this.postLogout().subscribe(() => {
            Swal.fire({
              title: "Sessão encerrada com sucesso",
              text: "Você pode logar novamente a hora que quiser!",
              icon: "success"
            });
            this.router.navigate(['/login']);
            this.isLoggedInSubject.next(false);
            this.roleSubject.next('');
          });
          localStorage.clear();
          sessionStorage.clear();
        }
      });
    } else {
      Swal.fire({
        title: "Você não possui uma sessão atual!",
        text: "Não se preocupe, já te redirecionamos para a página de login ;)",
        icon: "info"
      });
      this.router.navigate(['/login']);
    }
  }

  setLoginSubject(value: boolean){
    this.isLoggedInSubject.next(value);
  }

  setRoleSubject(value: string){
    this.roleSubject.next(value);
  }
}
