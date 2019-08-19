export class Country {
  id: number;
  countryName: string;
  code: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.countryName = (obj && obj.countryName) || '';
    this.code = (obj && obj.code) || '';
  }
}
