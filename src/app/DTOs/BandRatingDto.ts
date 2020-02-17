import {Band} from '../Class/Band';

export class BandRatingDto {
  band: Band;
  avgRating: number;
  ratedCount: number;
  constructor(obj: any) {
    this.band = obj.rated;
    this.avgRating = obj.avgRating;
    this.ratedCount = obj.ratedCount;
  }
}
