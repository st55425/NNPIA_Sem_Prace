export interface Court {
  id: number
  name: string
}

export interface AnonymizedReservation{
  id: number
  timeFrom: Date,
  timeTo: Date,
  reservableName: string
}

export interface CalendarData{
  title: string,
  start: Date,
  end: Date
}

export interface ReservablePrice{
  id: number,
  weekDays: boolean,
  weekendsAndHolidays: boolean,
  timeFrom: Date,
  timeTo: Date,
  price: number
}

export interface ReservableType{
  id: number,
  name: string,
  openFrom: Date,
  openTo: Date,
  prices?: ReservablePrice[]
}
