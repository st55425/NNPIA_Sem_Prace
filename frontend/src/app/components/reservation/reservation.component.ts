import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {CalendarData, Court, ReservableType} from "../../types";
import {ReservableService} from "../../services/reservable/reservable.service";
import {Observable} from "rxjs";
import {map, switchMap, tap} from "rxjs/operators";
import {ReservationService} from "../../services/reservation/reservation.service";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {ReservableTypeService} from "../../services/reservable-type/reservable-type.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  typeCtrl = new FormControl();
  courtCtrl = new FormControl();

  types!: ReservableType[];
  courts!: Court[];

  formGroup = this.fb.group({
    type: this.typeCtrl,
    court: this.courtCtrl
  })

  reservations!: CalendarData[];


  constructor(readonly reservableService: ReservableService, private fb: FormBuilder,
              readonly reservationService: ReservationService,
              readonly authService: AuthenticationService,
              readonly reservableTypeService: ReservableTypeService) {

    this.reservableTypeService.getReservableTypes().subscribe(types => {
      this.types = types;
      this.formGroup.get('type')?.setValue(this.types[0].id);
    });
    this.typeCtrl.valueChanges.pipe(
      switchMap(a => this.reservableService.getCourtsByTypeId(a))
    ).subscribe(data => {
      this.courts = data;
      if (data.length >0){
        this.formGroup.get('court')?.setValue(this.courts[0].id);
      }
    });
  }


  ngOnInit(): void {
    this.courtCtrl.valueChanges.pipe(
      switchMap(a => this.reservationService.getAnonymizedReservationsByCourt(a)),
      map(a => a.map(e => ({id: e.id, title: 'obsazeno', start: e.timeFrom, end: e.timeTo})))
    ).subscribe(data => this.reservations = data);
  }

}
