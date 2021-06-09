export interface Court {
  id: number
  name: string
}

export interface AnonymizedReservation{
  timeFrom: Date,
  timeTo: Date,
  reservationId: number
}

export interface CalendarData{
  title: string,
  start: Date,
  end: Date
}
