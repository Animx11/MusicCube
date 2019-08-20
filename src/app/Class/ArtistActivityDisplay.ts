export class ArtistActivityDisplay {
  artistId: number;
  artistName: string;
  activityPeriods: string[];
  current: boolean;

  getActivity(): string {
    return this.activityPeriods.toString();
  }
  constructor(id: number, name: string) {
    this.artistId = id;
    this.artistName = name;
    this.current = false;
    this.activityPeriods = [];
  }
  addToPeriods(period: string) {
    this.activityPeriods.push(period);
  }
  isCurrent() {
    this.current = true;
  }
}
