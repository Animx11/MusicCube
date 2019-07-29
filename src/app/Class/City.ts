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
}
