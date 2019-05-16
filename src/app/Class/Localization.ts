export class Localization {
  private id: number;
  private country: string;
  private city: string;
  private address: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.country = (obj && obj.country) || "";
    this.city = (obj && obj.city) || "";
    this.address = (obj && obj.address) || "";
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getCountry(): string {
    return this.country;
  }

  getCity(): string {
    return this.city;
  }

  getAddress(): string {
    return this.address;
  }

  // Setters

  setCountry(country: string) {
    this.country = country;
  }

  setCity(city: string) {
    this.city = city;
  }

  setAddress(address: string) {
    this.address = address;
  }
}
