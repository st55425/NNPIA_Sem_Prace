import { Component, OnInit } from '@angular/core';
import {ReservationService} from "../../services/reservation/reservation.service";
import {Observable} from "rxjs";
import {AnonymizedReservation} from "../../types";

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {

  username: string;

  futureReservations!: AnonymizedReservation[];

  pastReservations!: AnonymizedReservation[];

  constructor(readonly reservationService: ReservationService) {
    this.username = sessionStorage.getItem('username') ?? "";
  }

  ngOnInit(): void {
    this.reservationService.getFutureUserReservations(this.username).subscribe(data => this.futureReservations = data);
    this.reservationService.getPastUserReservations(this.username).subscribe(data => this.pastReservations = data);
  }

}
