import {Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Court, ReservableType} from "../../types";

@Injectable({
  providedIn: 'root'
})
export class ReservableTypeService {

  private reservableTypeUrl = "http://localhost:8080/reservabletypes";
  private headers = { 'content-type': 'application/json'};

  constructor(readonly http: HttpClient) { }

  getReservableTypes(){
    return this.http.get<ReservableType[]>(`${this.reservableTypeUrl}`);
  }

  deleteReservableType(id: number){
    this.http.delete(`${this.reservableTypeUrl}/${id}`).subscribe();
  }

  createReservableType(reservableType: ReservableType){
    return this.http.post<ReservableType>(this.reservableTypeUrl, JSON.stringify(reservableType),{'headers':this.headers});
  }

  updateReservableType(reservableType: ReservableType){
    return this.http.put<ReservableType>(this.reservableTypeUrl, JSON.stringify(reservableType),{'headers':this.headers});
  }
}
