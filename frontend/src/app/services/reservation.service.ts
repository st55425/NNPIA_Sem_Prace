import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AnonymizedReservation, Court} from "../types";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private reservationsUrl = "http://localhost:8080/reservations";

  constructor(readonly http: HttpClient) { }

  getAnonymizedReservationsByCourt(id: number){
    return this.http.get<AnonymizedReservation[]>(`${this.reservationsUrl}/${id}`);
  }
}
