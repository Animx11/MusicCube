import { Localization } from './Localization';

export class Band {

    private id: number;
    private bandName: string;
    private creationDate: Date;
    private formedIn: Localization;

    constructor(obj?: any) {
        this.bandName = (obj && obj.bandName) || '';
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

    getFormedIn(): Localization {
        return this.formedIn;
    }

    // Setters

    setBandName(bandName: string) {
        this.bandName = bandName;
    }

    setCreationDate(creationDate: Date) {
        this.creationDate = creationDate;
    }

    setFormedIn(formedIn: Localization) {
        this.formedIn = formedIn;
    }

}
