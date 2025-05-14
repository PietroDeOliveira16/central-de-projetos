import { Routes } from '@angular/router';
import { LandPageComponent } from './views/land-page/land-page.component';
import { CriarProjetoComponent } from './views/criar-projeto/criar-projeto.component';
import { LoginComponent } from './views/login/login.component';
import { CadastroComponent } from './views/cadastro/cadastro.component';
import { SaibaMaisComponent } from './views/saiba-mais/saiba-mais.component';
import { HomeComponent } from './views/home/home.component';
import { jwtGuard } from './auth/jwt_guard/jwt.guard';
import { loginGuard } from './auth/login_guard/login.guard';

export const routes: Routes = [
    {
        path:'',
        redirectTo:'land-page',
        pathMatch:'full'
    },
    {
        path:'land-page',
        component:LandPageComponent
    },
    {
        path:'login',
        component:LoginComponent,
        canActivate:[loginGuard]
    },
    {
        path:'cadastro',
        component:CadastroComponent
    },
    {
        path:'criar-projeto',
        component:CriarProjetoComponent,
        canActivate:[jwtGuard]
    },
    {
        path:'saiba-mais',
        component:SaibaMaisComponent
    },
    {
        path:'home',
        component:HomeComponent,
        canActivate:[jwtGuard]
    }
];