import { Routes } from '@angular/router';
import { LandPageComponent } from './views/land-page/land-page.component';
import { CriarProjetoComponent } from './views/criar-projeto/criar-projeto.component';
import { LoginComponent } from './views/login/login.component';
import { CadastroComponent } from './views/cadastro/cadastro.component';
import { SaibaMaisComponent } from './views/saiba-mais/saiba-mais.component';

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
    },
    {
        path:'saiba-mais',
        component:SaibaMaisComponent
    }
];