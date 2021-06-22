import {Component, OnDestroy, OnInit} from '@angular/core';
import {ReservationService} from "../../services/reservation/reservation.service";
import {Observable, Subscription} from "rxjs";
import {AnonymizedReservation, User} from "../../types";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {map} from "rxjs/operators";
import {FormControl} from "@angular/forms";
import {ifStmt} from "@angular/compiler/src/output/output_ast";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit, OnDestroy {

  usernameCtrl = new FormControl("");

  canChangeUserAsync = this.authService.roleAsync.pipe(map(role => role === "ADMIN"));

  futureReservations!: AnonymizedReservation[];

  pastReservations!: AnonymizedReservation[];

  users!: User[];

  constructor(private readonly reservationService: ReservationService,
              private readonly authService: AuthenticationService,
              private readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.authService.usernameAsync.subscribe(username => {
      this.usernameCtrl.valueChanges.subscribe(username => {
        this.reservationService.getFutureUserReservations(username).subscribe(data => this.futureReservations = data, error => {});
        this.reservationService.getPastUserReservations(username).subscribe(data => this.pastReservations = data, error => {});
      })
      this.usernameCtrl.setValue(username);
    });
    if (this.authService.roleSubject.getValue() === 'ADMIN'){
      this.userService.getAllUsers().subscribe(users => this.users = users);
    }
  }

  ngOnDestroy() {
  }
}
