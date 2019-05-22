export class Album {
  id: number;
  private albumName: string;
  private albumLengthSeconds: number;
  private releaseDate: Date;
  private company: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.albumName = (obj && obj.albumName) || "";
    this.albumLengthSeconds = (obj && obj.albumLengthSeconds) || 0;
    this.releaseDate = (obj && obj.releaseDate) || null;
    this.company = (obj && obj.company) || "";
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
}
