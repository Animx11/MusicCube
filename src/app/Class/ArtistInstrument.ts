import { Artist } from './Artist';
import { Instrument } from './Instrument';

export class ArtistInstrument {

    private id: number;
    private artist: Artist;
    private instrument: Instrument;

    constructor(obj?: any) {
        this.artist = (obj && obj.artist) || null;
        this.instrument = (obj && obj.instrument) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getArtist(): Artist {
        return this.artist;
    }

    getInstrument(): Instrument {
        return this.instrument;
    }

    // Setters

    setArtist(artist: Artist) {
        this.artist = artist;
    }

    setInstrument(instrument: Instrument) {
        this.instrument = instrument;
    }

}
