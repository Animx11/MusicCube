export class Country {
  id: number;
  countryName: string;
  code: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.countryName = (obj && obj.countryName) || '';
    this.code = (obj && obj.code) || '';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getCountryName(): string {
    return this.countryName;
  }

  getCode(): string {
    return this.code;
  }

  // Setters

  setCountryName(countryName: string) {
    this.countryName = countryName;
  }

  setCode(code: string) {
    this.code = code;
  }
  
}
