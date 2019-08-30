import { InstrumentType } from './InstrumentType';

export class Instrument {

  id: number;
  instrumentName: string;
  instrumentType: InstrumentType;
  about: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.instrumentName = (obj && obj.instrumentName) || '';
    this.instrumentType = (obj && obj.instrumentType) || '';
    this.about = (obj && obj.aboutInstrument) ||
      'No description was provided for this instrument. If you think, you could write some, please contact us.';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getInstrumentName(): string {
    return this.instrumentName;
  }

  getInstrumentType(): InstrumentType {
    return this.instrumentType;
  }

  getAbout() {
    return this.about;
  }

  // Setters

  setInstrumentName(instrumentName: string) {
    this.instrumentName = instrumentName;
  }

  setInstrumentType(instrumentType: InstrumentType) {
    this.instrumentType = instrumentType;
  }

  setAbout(about: string) {
    this.about = about;
  }
}
