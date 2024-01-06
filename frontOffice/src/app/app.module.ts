import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarMenuComponent } from './pages/sidebar-menu/sidebar-menu.component';
import { CategoriesComponent } from './pages/categories/categories.component';
import { UsersComponent } from './pages/users/users.component';
import { EnterprisesComponent } from './pages/enterprises/enterprises.component';
import { GamesComponent } from './pages/games/games.component';
import {HttpClientModule} from "@angular/common/http";
import {NotificationModule} from "./notification.module";
import {NotificationService} from "./services/notification.service";

@NgModule({
  declarations: [
    AppComponent,
    SidebarMenuComponent,
    CategoriesComponent,
    UsersComponent,
    EnterprisesComponent,
    GamesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NotificationModule
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
