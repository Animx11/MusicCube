export class Instrument {
  private id: number;
  private name: string;
  private type: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.name = (obj && obj.name) || "";
    this.type = (obj && obj.type) || "";
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getType(): string {
    return this.type;
  }

  // Setters

  setName(name: string) {
    this.name = name;
  }

  setType(type: string) {
    this.type = type;
  }
}
