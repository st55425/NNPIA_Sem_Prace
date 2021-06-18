import {Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ReservableType} from "../../types";

@Injectable({
  providedIn: 'root'
})
export class ReservableTypeService {

  private reservableTypeUrl = "http://localhost:8080/reservabletypes";

  constructor(readonly http: HttpClient) { }

  getReservableTypesWithPrices(){
    return this.http.get<ReservableType[]>(`${this.reservableTypeUrl}/prices`);
  }
}
