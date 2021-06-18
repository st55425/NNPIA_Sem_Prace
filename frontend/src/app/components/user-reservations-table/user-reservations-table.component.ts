import {Component, Input, OnInit} from '@angular/core';
import {AnonymizedReservation} from "../../types";
import {ReservationService} from "../../services/reservation.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-user-reservations-table',
  templateUrl: './user-reservations-table.component.html',
  styleUrls: ['./user-reservations-table.component.css'],
  providers: [MessageService]
})
export class UserReservationsTableComponent implements OnInit {

  @Input()
  anonymizedReservations!: AnonymizedReservation[];

  @Input()
  deletable?: boolean;

  cols!: any[];

  constructor(private readonly reservationService: ReservationService,
              private readonly messageService: MessageService) { }

  ngOnInit(): void {
    this.cols = [
      { field: 'court', header: 'Název Sportoviště' },
      { field: 'from', header: 'Začátek' },
      { field: 'to', header: 'Konec' },
    ];
    if (this.deletable){
      this.cols = [...this.cols, { field: 'delete', header: 'Odstranit rezervaci'}]
    }
  }

  deleteReservation(reservation: AnonymizedReservation){
    this.anonymizedReservations = this.anonymizedReservations.filter((p) => p.id !== reservation.id);
    this.messageService.add({severity: 'danger', summary: 'Rezervace odstraněna', detail:
        `Rezervace na kurt ${reservation.reservableName} v čase od ${reservation.timeFrom}
        do ${reservation.timeTo} byla odstraněna`});
    this.reservationService.deleteReservation(reservation.id);
  }

}
