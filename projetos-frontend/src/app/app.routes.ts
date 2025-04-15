import { Routes } from '@angular/router';
import { LandPageComponent } from './land-page/land-page.component';
import { CriarProjetoComponent } from './criar-projeto/criar-projeto.component';

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
        path:'criar-projeto',
        component:CriarProjetoComponent
    }
];
