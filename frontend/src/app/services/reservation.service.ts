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
    return this.http.get<AnonymizedReservation[]>(`${this.reservationsUrl}/anonym/${id}`);
  }

  getFutureUserReservations(username: string){
    return this.http.get<AnonymizedReservation[]>(`${this.reservationsUrl}/future/${username}`);
  }

  getPastUserReservations(username: string){
    return this.http.get<AnonymizedReservation[]>(`${this.reservationsUrl}/past/${username}`);
  }

  deleteReservation(id: number){
    this.http.delete(`${this.reservationsUrl}/${id}`).subscribe();
  }
}
