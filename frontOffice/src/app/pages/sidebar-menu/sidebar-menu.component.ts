import { Component } from '@angular/core';
import {MenuItem} from "../../interface/menu-item";

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent {
  menuItems: MenuItem[] = [
    { routerLink: 'y', routerLinkActive: 'active', iconClass: 'fas fa-bars', text: 'Menu' },
    { routerLink: 'enterprises', routerLinkActive: 'active', iconClass: 'fas fa-building', text: 'Enterprises' },
    { routerLink: 'games', routerLinkActive: 'active', iconClass: 'fas fa-gamepad', text: 'Games' },
    { routerLink: 'categories', routerLinkActive: 'active', iconClass: 'fas fa-list-alt', text: 'Categories' },
    { routerLink: 'users', routerLinkActive: 'active', iconClass: 'fas fa-users', text: 'Users' }
  ];
}
