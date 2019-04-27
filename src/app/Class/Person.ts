import { Location } from './Location';

export class Person {

    protected id: number;
    protected origin: Location;
    protected firstNames: string;
    protected lastName: string;
    protected birthDate: Date;

    constructor(obj?: any) {
        this.origin = (obj && obj.origin) || null;
        this.firstNames = (obj && obj.firstNames) || '';
        this.lastName = (obj && obj.lastName) || '';
        this.birthDate = (obj && obj.birthDate) || null;
    }

        // Getters

        getId(): number {
            return this.id;
        }
    
        getOrigin(): Location {
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

        setOrigin(origin: Location) {
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
