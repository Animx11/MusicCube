import { City } from './City';

export class Person {
  id: number;
  protected origin: City;
  protected firstNames: string;
  protected lastName: string;
  protected birthDate: Date;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.origin = (obj && obj.origin) || null;
    this.firstNames = (obj && obj.firstNames) || '';
    this.lastName = (obj && obj.lastName) || '';
    this.birthDate = (obj && obj.birthDate) || null;
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getOrigin(): City {
    return this.origin;
  }

  getFirstNames(): string {
    return this.firstNames;
  }

  getLastName(): string {
    return this.lastName;
  }

  getBirthDate(): Date {
    return this.birthDate;
  }

  // Setters

  setOrigin(origin: City) {
    this.origin = origin;
  }

  setFirstNames(firstNames: string) {
    this.firstNames = firstNames;
  }

  setLastName(lastName: string) {
    this.lastName = lastName;
  }

  setBirthDate(birthDate: Date) {
    this.birthDate = birthDate;
  }
}