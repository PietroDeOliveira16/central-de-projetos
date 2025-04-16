import { Routes } from '@angular/router';
import { LandPageComponent } from './land-page/land-page.component';
import { CriarProjetoComponent } from './criar-projeto/criar-projeto.component';
import { LoginComponent } from './login/login.component';
import { CadastroComponent } from './cadastro/cadastro.component';

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
        component:LoginComponent
    },
    {
        path:'cadastro',
        component:CadastroComponent
    },
    {
        path:'criar-projeto',
        component:CriarProjetoComponent
    }
];
