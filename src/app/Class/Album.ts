export class Album {
  id: number;
  private albumName: string;
  private albumLengthSeconds: number;
  private releaseDate: Date;
  private company: string;
  private coverArtLink: string;
  private type: string;
  private trackCount: number;


  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.albumName = (obj && obj.albumName) || '';
    this.albumLengthSeconds = (obj && obj.albumLengthSeconds) || 0;
    this.releaseDate = (obj && obj.releaseDate) || null;
    this.company = (obj && obj.company) || '';
    this.coverArtLink = (obj && obj.coverArtLink) || '';
    this.type = (obj && obj.type) || '';
    this.trackCount = (obj && obj.trackCount) || 0;
  }

  // Getters

  public getId(): number {
    return this.id;
  }

  public getAlbumName(): string {
    return this.albumName;
  }

  public getAlbumLengthSeconds(): number {
    return this.albumLengthSeconds;
  }

  public getReleaseDate(): Date {
    return this.releaseDate;
  }

  public getCompany(): string {
    return this.company;
  }

  public getCoverArtLink(): string {
    return this.coverArtLink;
  }

  public getType(): string {
    return this.type;
  }

  public getTrackCount(): number {
    return this.trackCount;
  }

  // Setters

  public setAlbumName(albumName: string) {
    return (this.albumName = albumName);
  }

  public setAlbumLengthSeconds(albumLengthSeconds: number) {
    return (this.albumLengthSeconds = albumLengthSeconds);
  }

  public setReleaseDate(releaseDate: Date) {
    return (this.releaseDate = releaseDate);
  }

  public setCompany(company: string) {
    return (this.company = company);
  }

  public setCoverArtLink(coverArtLink: string) {
    return this.coverArtLink = coverArtLink;
  }

  public setType(type: string) {
    return this.type = type;
  }

  public setTrackCount(trackCount: number) {
    return this.trackCount = trackCount;
  }
}
