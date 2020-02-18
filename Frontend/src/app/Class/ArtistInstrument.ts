import { Artist } from "./Artist";
import { Instrument } from "./Instrument";

export class ArtistInstrument {
  id: number;
  artist: Artist;
  instrument: Instrument;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
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
