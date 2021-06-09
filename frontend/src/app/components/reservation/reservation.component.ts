import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {AnonymizedReservation, CalendarData, Court} from "../../types";
import {ReservableService} from "../../services/reservable.service";
import {Observable} from "rxjs";
import {map, switchMap, tap} from "rxjs/operators";
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  courtCtrl = new FormControl();

  courts!: Court[];

  formGroup = this.fb.group({
    court: this.courtCtrl
  })

  reservations: Observable<CalendarData[]> = this.courtCtrl.valueChanges.pipe(
    switchMap(a => this.reservationService.getAnonymizedReservationsByCourt(a)),
    map(a => a.map(e => ({title: 'obsazeno', start: e.timeFrom, end: e.timeTo})))
    );


  constructor(readonly reservableService: ReservableService, private fb: FormBuilder,
              readonly reservationService: ReservationService) {
    reservableService.getCourts().subscribe(courts => {
      this.courts = courts;
      this.formGroup.get('court')?.setValue(this.courts[0].id);
    });
  }


  ngOnInit(): void {
  }

}
