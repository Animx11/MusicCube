import { Genre } from './Genre';
import { Instrument } from './Instrument';

export class GenreInstrument {

    private id: number;
    private genre: Genre;
    private instrument: Instrument;

    constructor(obj?: any) {
        this.genre = (obj && obj.genre) || null;
        this.instrument = (obj && obj.instrument) || null;
    }

}
