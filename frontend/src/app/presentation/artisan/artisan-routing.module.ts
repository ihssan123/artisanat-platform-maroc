import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtisanComponent } from './artisan.component';

const routes: Routes = [
  {
    path: 'profil',
    component: ArtisanComponent // Composant principal pour la route racine
  }
  // Vous pouvez ajouter d'autres routes ici comme :
  // { path: 'detail/:id', component: ArtisanDetailComponent },
  // { path: 'nouveau', component: ArtisanCreateComponent },
  // etc.
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArtisanRoutingModule { }