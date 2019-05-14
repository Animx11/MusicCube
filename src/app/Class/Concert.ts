import { Localization } from "src/app/Class/Localization";

export class Concert {
  private id: number;
  private startTime: Date;
  private concertName: string;
  private localization: Localization;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.startTime = (obj && obj.startTime) || null;
    this.concertName = (obj && obj.concertName) || "";
    this.localization = (obj && obj.localization) || null;
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

  getLocalization(): Localization {
    return this.localization;
  }

  // Setters

  setStartTime(startTime: Date) {
    this.startTime = startTime;
  }

  setConcertname(concertName: string) {
    this.concertName = concertName;
  }

  setLocalization(localization: Localization) {
    this.localization = localization;
  }
}
