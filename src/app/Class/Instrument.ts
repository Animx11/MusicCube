export class Instrument {
  private id: number;
  private instrumentName: string;
  private type: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.instrumentName = (obj && obj.instrumentName) || "";
    this.type = (obj && obj.type) || "";
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getInstrumentName(): string {
    return this.instrumentName;
  }

  getType(): string {
    return this.type;
  }

  // Setters

  setInstrumentName(instrumentName: string) {
    this.instrumentName = instrumentName;
  }

  setType(type: string) {
    this.type = type;
  }
}
