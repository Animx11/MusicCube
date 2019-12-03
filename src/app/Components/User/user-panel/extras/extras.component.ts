import { Component, OnInit } from '@angular/core';
import {UserAlbumStatusService} from '../../../../Services/user-album-status.service';
import {UserSongStatusService} from '../../../../Services/user-song-status.service';
import {TokenStorageService} from '../../../../Services/token-storage.service';
import {Song} from '../../../../Class/Song';
import {Album} from '../../../../Class/Album';

@Component({
  selector: 'app-extras',
  templateUrl: './extras.component.html',
  styleUrls: ['./extras.component.css']
})
export class ExtrasComponent implements OnInit {

  isYourSongsClicked: boolean;
  isToListenClicked: boolean;
  isOwnedAlbumsClicked: boolean;
  isSoughtAlbumsClicked: boolean;

  songList: Song[];
  albumList: Album[];
  constructor(
    private userAlbumService: UserAlbumStatusService,
    private userSongService: UserSongStatusService,
    private tokenService: TokenStorageService
  ) { }

  ngOnInit() {
  }

  reset() {
    this.isOwnedAlbumsClicked =
    this.isSoughtAlbumsClicked =
    this.isYourSongsClicked =
    this.isToListenClicked = false;
  }

  showListenedSongs() {
    this.reset();
    this.isYourSongsClicked = true;
    this.userSongService.getListened(this.tokenService.getUsername()).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  showSongsToListen() {
    this.reset();
    this.isToListenClicked = true;

  }

  showOwnedAlbums() {
    this.reset();
    this.isOwnedAlbumsClicked = true;

  }

  showSoughtAlbums() {
    this.reset();
    this.isSoughtAlbumsClicked = true;

  }
}
