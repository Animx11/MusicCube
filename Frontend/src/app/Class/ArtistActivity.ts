import { Artist } from './Artist';
import { Band } from './Band';

export class ArtistActivity {
  id: number;
  artist: Artist;
  band: Band;
  activityStart: Date;
  activityEnd: Date;
  isActive: boolean;
  roles: string[];
  tempRole: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.artist = (obj && obj.artist) || null;
    this.band = (obj && obj.band) || null;
    this.activityStart = (obj && obj.activityStart) || null;
    this.activityEnd = (obj && obj.activityEnd) || null;
    this.isActive = (obj && obj.active) || null;
    this.roles = (obj && obj.roles) || [];
    this.tempRole = '';
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

  getRoles(): string[] {
    return this.roles;
  }

  // Setters

  setArtist(artist: Artist) {
    this.artist = artist;
  }

  setBand(band: Band) {
    this.band = band;
  }

  setActivityStart(date: Date) {
    this.activityStart = date;
  }

  setActivityEnd(date: Date) {
    this.activityEnd = date;
  }

  setIsActive(isActive: boolean) {
    this.isActive = isActive;
  }

  setRoles(roles: string[]) {
    this.roles = roles;
  }

}
