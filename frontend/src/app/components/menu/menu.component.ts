import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  items!: MenuItem[];

  constructor() { }

  ngOnInit(): void {
    this.items = [
      {label: 'Rezervace', routerLink: "/rezervace"},
      {label: 'Ceník', routerLink: "/cenik"},
      {label: 'Log in', routerLink: "/login"}
    ];
  }

}
