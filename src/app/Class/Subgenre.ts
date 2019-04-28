import { Genre } from './Genre';

export class Subgenre {

    private id: number;
    private parentGenre: Genre;
    private childGenre: Genre;

    constructor(obj?: any) {
        this.parentGenre = (obj && obj.parentGenre) || null;
        this.childGenre = (obj && obj.childGenre) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getParentGenre(): Genre {
        return this.parentGenre;
    }

    getChildGenre(): Genre {
        return this.childGenre;
    }

    // Setters

    setParentGenre(parentGenre: Genre) {
        this.parentGenre = parentGenre;
    }

    setChildGenre(childGenre: Genre) {
        this.childGenre = childGenre;
    }

}
