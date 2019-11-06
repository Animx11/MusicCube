import { City } from './City';


export class Concert {
  id: number;
  startTime: Date;
  concertName: string;
  concertCity: City;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.startTime = (obj && obj.startTime) || null;
    this.concertName = (obj && obj.concertName) || "";
    this.concertCity = (obj && obj.concertCity) || null;
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getStartTime(): Date {
    return this.startTime;
  }

  getConcertName(): string {
    return this.concertName;
  }

  getConcertCity(): City {
    return this.concertCity;
  }

  // Setters

  setStartTime(startTime: Date) {
    this.startTime = startTime;
  }

  setConcertName(concertName: string) {
    this.concertName = concertName;
  }

  setConcertCity(concertCity: City) {
    this.concertCity = concertCity;
  }
}
