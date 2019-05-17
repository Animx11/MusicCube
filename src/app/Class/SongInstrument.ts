import { Song } from "./Song";
import { Instrument } from "./Instrument";

export class SongInstrument {
  private id: number;
  private song: Song;
  private instrument: Instrument;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.song = (obj && obj.song) || null;
    this.instrument = (obj && obj.instrument) || null;
  }
}
