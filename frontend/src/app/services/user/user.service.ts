import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../types";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = "http://localhost:8080/users";

  constructor(readonly http: HttpClient) { }

  getAllUsers(){
    return this.http.get<User[]>(this.usersUrl);
  }
}
