import { Component } from '@angular/core';
import {MenuItem} from "../../interface/menu-item";

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent {
  menuItems: MenuItem[] = [
    { routerLink: '', routerLinkActive: 'active', iconClass: 'fas fa-bars', text: 'Menu' },
    { routerLink: 'enterprises', routerLinkActive: 'active', iconClass: 'fas fa-building', text: 'Enterprises' },
    { routerLink: 'games', routerLinkActive: 'active', iconClass: 'fas fa-gamepad', text: 'Games' },
    { routerLink: 'categories', routerLinkActive: 'active', iconClass: 'fas fa-list-alt', text: 'Categories' },
    { routerLink: 'users', routerLinkActive: 'active', iconClass: 'fas fa-users', text: 'Users' },
    { routerLink: 'payments', routerLinkActive: 'active', iconClass: 'fas fa-dollar-sign', text: 'Payments' },
    { routerLink: 'orders', routerLinkActive: 'active', iconClass: 'fas fa-shopping-cart', text: 'Orders' },
    { routerLink: 'deliveries', routerLinkActive: 'active', iconClass: 'fas fa-truck', text: 'Deliveries' },
    { routerLink: 'quizzes', routerLinkActive: 'active', iconClass: 'fas fa-question', text: 'Quizzes' },
    { routerLink: 'questions', routerLinkActive: 'active', iconClass: 'fas fa-question-circle', text: 'Questions' }
  ];
}
