import {Song} from '../Class/Song';

export class SongRatingDto {
  song: Song;
  avgRating: number;
  ratedCount: number;
  constructor(obj: any) {
    this.song = obj.rated;
    this.avgRating = obj.avgRating;
    this.ratedCount = obj.ratedCount;
  }
}
