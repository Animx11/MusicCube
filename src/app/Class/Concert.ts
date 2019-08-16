export class Concert {
  id: number;
  private startTime: Date;
  private concertName: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.startTime = (obj && obj.startTime) || null;
    this.concertName = (obj && obj.concertName) || '';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getStartTime(): Date {
    return this.startTime;
  }

  getConcertName(): string {
    return this.concertName;
  }

  // Setters

  setStartTime(startTime: Date) {
    this.startTime = startTime;
  }

  setConcertName(concertName: string) {
    this.concertName = concertName;
  }
}
