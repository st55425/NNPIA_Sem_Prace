import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {LoginComponent} from "./components/login/login.component";
import {AuthGaurdService} from "./services/auth-gaurd-service.service";
import {LogoutComponent} from "./components/logout/logout.component";
import {UserReservationsComponent} from "./components/user-reservations/user-reservations.component";

const routes: Routes = [
  {path: 'rezervace', component: ReservationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'cenik', component: PageNotFoundComponent, canActivate: [AuthGaurdService]},
  {path: 'logout', component: LogoutComponent},
  {path: 'mojerezervace', component: UserReservationsComponent, canActivate: [AuthGaurdService]},
  {path: '',   component: ReservationComponent, pathMatch: 'full' },
  {path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
