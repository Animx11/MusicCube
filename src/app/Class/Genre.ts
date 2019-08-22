import { Country } from './Country';

export class Genre {
  id: number;
  origin: Country;
  genreName: string;
  creationDate: string;
  aboutGenre: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.origin = (obj && obj.origin) || null;
    this.genreName = (obj && obj.genreName) || '';
    this.creationDate = (obj && obj.creationDate) || '';
    this.aboutGenre = (obj && obj.aboutGenre) || '';
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

  getAboutGenre(): string {
    return this.aboutGenre;
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

  setAboutGenre(aboutGenre: string) {
    this.aboutGenre = aboutGenre;
  }
}
