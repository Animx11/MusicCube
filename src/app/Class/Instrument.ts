import { InstrumentType } from './InstrumentType';

export class Instrument {

  id: number;
  instrumentName: string;
  private instrumentType: InstrumentType;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.instrumentName = (obj && obj.instrumentName) || '';
    this.instrumentType = (obj && obj.instrumentType) || '';
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

  // Setters

  setInstrumentName(instrumentName: string) {
    this.instrumentName = instrumentName;
  }

  setInstrumentType(instrumentType: InstrumentType) {
    this.instrumentType = instrumentType;
  }
}
