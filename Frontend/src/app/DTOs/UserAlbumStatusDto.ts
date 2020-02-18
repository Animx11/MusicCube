export class UserAlbumStatusDto {
  userName: string;
  albumId: number;
  owned: boolean;
  sought: boolean;

  constructor(
    userName: string,
    albumId: number,
    owned: boolean,
    sought: boolean
  ) {
    this.userName = userName;
    this.albumId = albumId;
    this.owned = owned;
    this.sought = sought;
  }
}
