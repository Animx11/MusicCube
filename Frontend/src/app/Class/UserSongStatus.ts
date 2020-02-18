import {Users} from './Users';
import {Song} from './Song';

export class UserSongStatus {
  id: number;
  song: Song;
  user: Users;
  listened: boolean;
  toListen: boolean;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.song = (obj && obj.song) || null;
    this.user = (obj && obj.user) || null;
    this.listened = (obj && obj.owned) || false;
    this.toListen = (obj && obj.sought) || false;
  }
}
