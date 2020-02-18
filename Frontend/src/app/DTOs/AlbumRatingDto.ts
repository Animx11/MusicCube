import {Album} from '../Class/Album';

export class AlbumRatingDto {
  album: Album;
  avgRating: number;
  ratedCount: number;
  constructor(obj: any) {
    this.album = obj.rated;
    this.avgRating = obj.avgRating;
    this.ratedCount = obj.ratedCount;
  }
}
