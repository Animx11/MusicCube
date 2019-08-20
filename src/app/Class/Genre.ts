import { Country } from './Country';

export class Genre {
  id: number;
  origin: Country;
  genreName: string;
  creationDate: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.origin = (obj && obj.origin) || null;
    this.genreName = (obj && obj.genreName) || '';
    this.creationDate = (obj && obj.creationDate) || '';
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getOrigin(): Country {
    return this.origin;
  }

  getGenreName(): string {
    return this.genreName;
  }

  getCreationDate(): string {
    return this.creationDate;
  }

  // Setters

  setOrigin(origin: Country) {
    this.origin = origin;
  }

  setGenreName(genreName: string) {
    this.genreName = genreName;
  }

  setCreationDate(creationDate: string) {
    this.creationDate = creationDate;
  }
}
