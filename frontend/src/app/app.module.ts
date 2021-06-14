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
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {DropdownModule} from "primeng/dropdown";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { LoginComponent } from './components/login/login.component';
import {InputTextModule} from "primeng/inputtext";
import {PasswordModule} from "primeng/password";
import {AuthHtppInterceptorService} from "./services/auth-htpp-interceptor-service.service";
import {MessageModule} from "primeng/message";
import { LogoutComponent } from './components/logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ReservationCalendarComponent,
    PageNotFoundComponent,
    ReservationComponent,
    LoginComponent,
    LogoutComponent,
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
    BrowserAnimationsModule,
    InputTextModule,
    PasswordModule,
    MessageModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthHtppInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
