import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu.component';
import {MenubarModule} from "primeng/menubar";
import { ReservationCalendarComponent } from './components/reservation-calendar/reservation-calendar.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import {ButtonModule} from "primeng/button";
import {FullCalendarModule} from "primeng/fullcalendar";
import { ReservationComponent } from './components/reservation/reservation.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ReservationCalendarComponent,
    PageNotFoundComponent,
    ReservationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenubarModule,
    ButtonModule,
    FullCalendarModule,
    HttpClientModule,
    ReactiveFormsModule,
    DropdownModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
