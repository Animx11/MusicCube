import { Band } from "./Band";
//import { Concert } from './Concert';

export class BandConcert {
  id: number;
  private band: Band;
  // private concert: Concert;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.band = (obj && obj.band) || null;
    //this.concert = (obj && obj.concert) || null;
  }
  /*
    // Getters

    getId(): number {
        return this.id;
    }

    getBand(): Band {
        return this.band;
    }
    
    getConcert(): Concert {
        return this.concert;
    }

    // Setter

    setBand(band: Band) {
        this.band = band;
    }

    setConcert(concert: Concert) {
        this.concert = concert;
    }
*/
}
