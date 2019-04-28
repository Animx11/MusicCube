import { Location } from './Location';

export class Genre {

    private id: number;
    private origin: Location;
    private genreName: string;
    private creationDate: Date;

    constructor(obj?: any) {
        this.origin = (obj && obj.origin) || null;
        this.genreName = (obj && obj.genreName) || '';
        this.creationDate = (obj && obj.creationDate) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getOrigin(): Location {
        return this.origin;
    }

    getGenreName(): string {
        return this.genreName;
    }

    getCreationDate(): Date {
        return this.creationDate;
    }

    // Setters

    setOrigin(origin: Location) {
        this.origin = origin;
    }

    setGenreName(genreName: string) {
        this.genreName = genreName;
    }

    setCreationDate(creationDate: Date) {
        this.creationDate = creationDate;
    }

}
