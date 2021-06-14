import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {AuthenticationService} from "../../services/authentication-service.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  items!: MenuItem[];

  constructor(private readonly authService: AuthenticationService) { }

  ngOnInit(): void {
    this.authService.roleAsync.subscribe(role =>{
      if (role === ''){
        this.items = [
          {label: 'Rezervace', routerLink: "/rezervace"},
          {label: 'Ceník', routerLink: "/cenik"},
          {label: 'Log in', routerLink: "/login"},
        ];
      }
      else if (role === 'USER'){
        this.items = [
          {label: 'Rezervace', routerLink: "/rezervace"},
          {label: 'Ceník', routerLink: "/cenik"},
          {label: 'Logout', routerLink: "/logout"},
          {label: 'Moje rezervace', routerLink: '/mojerezervace'},
        ];
      }
      else if (role === 'STAFF'){
        this.items = [
          {label: 'Rezervace', routerLink: "/rezervace"},
          {label: 'Ceník', routerLink: "/cenik"},
          {label: 'Logout', routerLink: "/logout"},
        ];
      }
      else {
        this.items = [
          {label: 'Rezervace', routerLink: "/rezervace"},
          {label: 'Ceník', routerLink: "/cenik"},
          {label: 'Logout', routerLink: "/logout"},
          {label: 'Přehled sportovišť', routerLink: '/sportoviste'},
        ];
      }
    })
  }

}
