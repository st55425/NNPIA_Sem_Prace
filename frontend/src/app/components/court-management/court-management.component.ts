import { Component, OnInit } from '@angular/core';
import {Court, ReservableType, Reservation} from "../../types";
import {ReservableTypeService} from "../../services/reservable-type/reservable-type.service";
import {ReservableService} from "../../services/reservable/reservable.service";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {NewReservationFormComponent} from "../new-reservation-form/new-reservation-form.component";
import {ReservableEditFormComponent} from "../reservable-edit-form/reservable-edit-form.component";

@Component({
  selector: 'app-court-management',
  templateUrl: './court-management.component.html',
  styleUrls: ['./court-management.component.css'],
  providers: [DialogService]
})
export class CourtManagementComponent implements OnInit {

  reservableTypes!: ReservableType[];

  cols: any[];

  constructor(private readonly reservableTypeService: ReservableTypeService,
              private readonly reservableService: ReservableService,
              private readonly dialogService: DialogService) {
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

  editCourt(court?: Court) {
    let config = {
      header: '',
      width: '70%',
      contentStyle: {"height": "400px", "overflow": "auto"},
      data: {}
    };

    if (court){
      config.header = 'Úprava sportoviště';
        config.data = {court: court}
      } else {
      config.header = "Nové sportoviště"
    }
    const ref = this.dialogService.open(ReservableEditFormComponent, config)

    ref.onClose.subscribe((court: Court) =>{
        if (court){
          this.reservableTypeService.getReservableTypesWithCourts().subscribe(data => this.reservableTypes = data);
        }
    });
  }

  deleteCourt(court: Court) {
    if (court.id){
      this.reservableService.deleteCourt(court.id);
      this.reservableTypeService.getReservableTypesWithCourts().subscribe(data => this.reservableTypes = data);
    }
  }
}
