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

  getName(): string {
    return this.instrumentname;
  }

  getType(): string {
    return this.type;
  }

  // Setters

  setName(instrument: string) {
    this.instrumentName = instrumentName;
  }

  setType(type: string) {
    this.type = type;
  }
}
