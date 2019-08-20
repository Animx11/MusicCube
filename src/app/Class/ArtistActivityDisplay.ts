import {isNull} from 'util';
import {ArtistInBand} from './ArtistInBand';

export class ArtistActivityDisplay {

  constructor(id: number, name: string, bandId?: number, bandName?: string) {
    this.artistId = id;
    this.artistName = name;
    this.current = false;
    this.activityPeriods = [];
    if (bandId) { this.bandId = bandId; }
    if (bandName) { this.bandName = bandName; }
  }

  artistId: number;
  artistName: string;
  bandId: number;
  bandName: string;
  activityPeriods: string[];
  current: boolean;

  static buildPeriondString(el: ArtistInBand): string {
    let periodString = '';
    const begin = isNull(el.activityStart) ? null : new Date(el.activityStart);
    const end = isNull(el.activityEnd) ? null : new Date(el.activityEnd);
    /*
      Checking all possibilities, in order to properly describe, when artist was active
     */
    if (isNull(begin)) {
      if (isNull(end)) { // all null
        periodString = ' unknown';
      } else {
        periodString = (' ? - ' + end.getFullYear().toString()); // only end is known
      }
    } else if (isNull(end)) { // only start...
      if (el.isActive) { // ...and artist is still active
        periodString = (' since ' + begin.getFullYear().toString());
      } else { // ...and artist is inactive, but end time is unknown
        periodString = (' ' + begin.getFullYear().toString() + ' - ?');
      }
    } else if (begin.getFullYear() === end.getFullYear()) { // no nulls, active less than one year
      periodString = (' ' + begin.getFullYear().toString());
    } else { // no nulls, active multiple years
      periodString = (
        ' ' +
        begin.getFullYear().toString() +
        ' - ' +
        end.getFullYear().toString()
      );
    }
    return periodString;
  }

  getActivity(): string {
    return this.activityPeriods.toString();
  }
  addToPeriods(period: string) {
    this.activityPeriods.push(period);
  }
  isCurrent() {
    this.current = true;
  }
}
