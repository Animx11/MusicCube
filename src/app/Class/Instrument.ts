import { InstrumentType } from './InstrumentType';

export class Instrument {

  id: number;
  instrumentName: string;
  private instrumentTypeName: InstrumentType;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.instrumentName = (obj && obj.instrumentName) || '';
    this.instrumentTypeName = (obj && obj.instrumentTypeName) || '';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getInstrumentName(): string {
    return this.instrumentName;
  }

  getInstrumentTypeName(): InstrumentType {
    return this.instrumentTypeName;
  }

  // Setters

  setInstrumentName(instrumentName: string) {
    this.instrumentName = instrumentName;
  }

  setInstrumentTypeName(instrumentTypeName: InstrumentType) {
    this.instrumentTypeName = instrumentTypeName;
  }
}
