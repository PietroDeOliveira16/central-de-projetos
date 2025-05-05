import { Routes } from '@angular/router';
import { LandPageComponent } from './views/land-page/land-page.component';
import { CriarProjetoComponent } from './views/criar-projeto/criar-projeto.component';
import { LoginComponent } from './views/login/login.component';
import { CadastroComponent } from './views/cadastro/cadastro.component';
import { SaibaMaisComponent } from './views/saiba-mais/saiba-mais.component';
import { HomeComponent } from './views/home/home.component';
import { LogoutComponent } from './components/logout/logout.component';
import { jwtGuard } from './auth/guard/jwt.guard';

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
        component:CriarProjetoComponent,
        canActivate:[jwtGuard]
    },
    {
        path:'saiba-mais',
        component:SaibaMaisComponent
    },
    {
        path:'home',
        component:HomeComponent
    },
    {
        path:'logout',
        component:LogoutComponent
    }
];