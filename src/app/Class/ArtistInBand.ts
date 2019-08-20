import { Artist } from "./Artist";
import { Band } from "./Band";

export class ArtistInBand {
  id: number;
  private artist: Artist;
  private band: Band;
  private activityStart: Date;
  private activityEnd: Date;
  private isActive: boolean;
  private roles: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.artist = (obj && obj.artist) || null;
    this.band = (obj && obj.band) || null;
    this.activityStart = (obj && obj.activityStart) || null;
    this.activityEnd = (obj && obj.activityEnd) || null;
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

  getActivityStart(): Date {
    return this.activityStart;
  }

  getActivityEnd(): Date {
    return this.activityEnd;
  }

  getIsActive(): boolean {
    return this.isActive;
  }

  getRoles(): string {
    return this.roles;
  }

}
