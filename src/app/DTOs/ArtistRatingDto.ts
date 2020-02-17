import {Artist} from '../Class/Artist';

export class ArtistRatingDto {
  artist: Artist;
  avgRating: number;
  ratedCount: number;
  constructor(obj: any) {
    this.artist = obj.rated;
    this.avgRating = obj.avgRating;
    this.ratedCount = obj.ratedCount;
  }
}
