import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {UsersComponent} from "./pages/users/users.component";
import {CategoriesComponent} from "./pages/categories/categories.component";
import {GamesComponent} from "./pages/games/games.component";
import {EnterprisesComponent} from "./pages/enterprises/enterprises.component";

const routes: Routes = [
  {
    path: '',
    component: AppComponent
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path:'categories',
    component: CategoriesComponent
  },
  {
    path:'games',
    component: GamesComponent
  },
  {
    path:'enterprises',
    component:EnterprisesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
