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

export interface TableColumnsDefinition{
  field: string,
  header: string
}
