export class Location {

    private id: number;
    private country: string;
    private city: string;

    constructor(obj?: any) {
        this.country = (obj && obj.country) || '';
        this.city = (obj && obj.city) || '';
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

    // Setters

    setCountry(country: string) {
        this.country = country;
    }

    setCity(city: string) {
        this.city = city;
    }

}
