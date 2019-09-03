import {Country} from './Country';

export class City {
  id: number;
  cityName: string;
  country: Country;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.cityName = (obj && obj.cityName) || '';
    this.country = (obj && obj.country) || '';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getCityName(): string {
    return this.cityName;
  }

  getCountry(): Country {
    return this.country;
  }

  // Setters

  setCityName(cityName: string) {
    this.cityName = cityName;
  }

  setCountry(country: Country) {
    this.country = country;
  }

}
