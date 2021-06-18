import { Component, OnInit } from '@angular/core';
import {Court, ReservableType} from "../../types";
import {ReservableTypeService} from "../../services/reservable-type/reservable-type.service";

@Component({
  selector: 'app-court-management',
  templateUrl: './court-management.component.html',
  styleUrls: ['./court-management.component.css']
})
export class CourtManagementComponent implements OnInit {

  reservableTypes!: ReservableType[];

  cols: any[];

  constructor(private readonly reservableTypeService: ReservableTypeService) {
    this.cols = [
      {field: 'expand', header: ''},
      { field: 'name', header: 'Typ Sportoviště'},
      { field: 'openFrom', header: 'Otevřeno od' },
      { field: 'openTo', header: 'Otevřeno do' },
      { field: 'buttons', header: '' },
    ];
  }

  ngOnInit(): void {
    this.reservableTypeService.getReservableTypesWithCourts().subscribe(data => this.reservableTypes = data);
  }

  editReservableType(reservableType: ReservableType) {

  }

  deleteReservableType(reservableType: ReservableType) {

  }

  editCourt(court: Court) {

  }
  deleteCourt(court: Court) {

  }
}
