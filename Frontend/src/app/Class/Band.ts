import {City} from './City';

export class Band {
  id: number;
  bandName: string;
  creationDate: Date;
  formedIn: City;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.bandName = (obj && obj.bandName) || "";
    this.creationDate = (obj && obj.creationDate) || null;
    this.formedIn = (obj && obj.formedIn) || null;
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getBandName(): string {
    return this.bandName;
  }

  getCreationDate(): Date {
    return this.creationDate;
  }

  getFormedIn(): City {
    return this.formedIn;
  }

  // Setters

  setBandName(bandName: string) {
    this.bandName = bandName;
  }

  setCreationDate(creationDate: Date) {
    this.creationDate = creationDate;
  }

  setFormedIn(formedIn: City) {
    this.formedIn = formedIn;
  }
}
