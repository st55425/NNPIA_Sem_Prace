import { Component, OnInit } from '@angular/core';
import {ReservationService} from "../../services/reservation.service";
import {Observable} from "rxjs";
import {AnonymizedReservation} from "../../types";

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {

  username: string;

  futureReservations: Observable<AnonymizedReservation[]>;

  pastReservations: Observable<AnonymizedReservation[]>;

  constructor(readonly reservationService: ReservationService) {
    this.username = sessionStorage.getItem('username') ?? "";
    this.futureReservations = reservationService.getFutureUserReservations(this.username);
    this.pastReservations = reservationService.getPastUserReservations(this.username);
  }

  ngOnInit(): void {
  }

}
