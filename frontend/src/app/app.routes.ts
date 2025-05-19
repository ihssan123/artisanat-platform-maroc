import { Routes } from '@angular/router';
export const routes: Routes = [
  {
  path: '',
  loadComponent: () => import('./presentation/auth/auth.component').then(m => m.AuthComponent)
},
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: 'full',
  },
  {
    path: 'artisan/profil',
   loadComponent: () => import('./presentation/artisan/artisan.component').then(m => m.ArtisanComponent)
  },
];
