import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Court} from "../../types";

@Injectable({
  providedIn: 'root'
})
export class ReservableService {

  private courtsUrl = "http://localhost:8080/courts";

  constructor(readonly http: HttpClient) { }

  getCourts(){
    return this.http.get<Court[]>(this.courtsUrl);
  }

  getCourtsByTypeId(id: number){
    return this.http.get<Court[]>(`${this.courtsUrl}/types/${id}`);
  }

  deleteCourt(id: number){
    this.http.delete(`${this.courtsUrl}/${id}`).subscribe();
  }
}
