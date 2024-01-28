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
import {EnterpriseDetailsComponent} from "./pages/enterprise-details/enterprise-details.component";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    SidebarMenuComponent,
    CategoriesComponent,
    UsersComponent,
    EnterprisesComponent,
    GamesComponent,
    EnterpriseDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NotificationModule,
    ReactiveFormsModule
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
