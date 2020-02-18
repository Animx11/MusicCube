import {Album} from './Album';
import {Users} from './Users';

export class UserAlbumStatus {
  id: number;
  album: Album;
  user: Users;
  owned: boolean;
  sought: boolean;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.album = (obj && obj.album) || null;
    this.user = (obj && obj.user) || null;
    this.owned = (obj && obj.owned) || false;
    this.sought = (obj && obj.sought) || false;
  }
}
