import { Person } from "./Person";

export class Artist extends Person {
  private stageName: string;

  constructor(obj?: any) {
    super(obj);
    this.stageName = (obj && obj.stageName) || "";
  }

  // Getter

  getStageName(): string {
    return this.stageName;
  }

  // Setter

  setStageName(stageName: string) {
    this.stageName = stageName;
  }
}
