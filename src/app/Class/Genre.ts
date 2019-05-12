import { Localization } from './Localization';

export class Genre {

    private id: number;
    private origin: Localization;
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

    getOrigin(): Localization {
        return this.origin;
    }

    getGenreName(): string {
        return this.genreName;
    }

    getCreationDate(): Date {
        return this.creationDate;
    }

    // Setters

    setOrigin(origin: Localization) {
        this.origin = origin;
    }

    setGenreName(genreName: string) {
        this.genreName = genreName;
    }

    setCreationDate(creationDate: Date) {
        this.creationDate = creationDate;
    }

}
