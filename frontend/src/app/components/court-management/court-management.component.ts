import { Component, OnInit } from '@angular/core';
import {Court, ReservableType} from "../../types";
import {ReservableTypeService} from "../../services/reservable-type/reservable-type.service";
import {ReservableService} from "../../services/reservable/reservable.service";

@Component({
  selector: 'app-court-management',
  templateUrl: './court-management.component.html',
  styleUrls: ['./court-management.component.css']
})
export class CourtManagementComponent implements OnInit {

  reservableTypes!: ReservableType[];

  cols: any[];

  constructor(private readonly reservableTypeService: ReservableTypeService,
              private readonly reservableService: ReservableService) {
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
    this.reservableTypeService.deleteReservableType(reservableType.id);
    this.reservableTypes = this.reservableTypes.filter((p) => p.id !== reservableType.id);
  }

  editCourt(court: Court) {

  }
  deleteCourt(court: Court) {
    this.reservableService.deleteCourt(court.id);
    this.reservableTypes.find(a =>a.reservableList = a.reservableList?.filter((p) => p.id !== court.id));
  }
}