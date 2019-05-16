import { Artist } from "./Artist";
import { Band } from "./Band";

export class ArtistInBand {
  private id: number;
  private artist: Artist;
  private band: Band;
  private yearsActive: string;
  private isActive: boolean;
  private roles: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.artist = (obj && obj.artist) || null;
    this.band = (obj && obj.band) || null;
    this.yearsActive = (obj && obj.yearsActive) || "";
    this.isActive = (obj && obj.isActive) || null;
    this.roles = (obj && obj.roles) || "";
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getArtist(): Artist {
    return this.artist;
  }

  getBand(): Band {
    return this.band;
  }

  getYearsActive(): string {
    return this.yearsActive;
  }

  getIsActive(): boolean {
    return this.isActive;
  }

  getRoles(): string {
    return this.roles;
  }
}
