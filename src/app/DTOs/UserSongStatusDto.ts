export class UserSongStatusDto {
  userName: string;
  songId: number;
  listened: boolean;
  toListen: boolean;
  constructor(
    userName: string,
    songId: number,
    listened: boolean,
    toListen: boolean
  ) {
    this.userName = userName;
    this.songId = songId;
    this.listened = listened;
    this.toListen = toListen;
  }
}
