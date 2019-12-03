import { Component, OnInit } from '@angular/core';
import {UserSongStatus} from '../../../../Class/UserSongStatus';
import {UserAlbumStatus} from '../../../../Class/UserAlbumStatus';

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

  songList: UserSongStatus[];
  albumList: UserAlbumStatus[];
  constructor() { }

  ngOnInit() {
  }

  reset() {
    this.isOwnedAlbumsClicked =
    this.isSoughtAlbumsClicked =
    this.isYourSongsClicked =
    this.isToListenClicked = false;
  }

  showListenedSongs() {

  }

  showSongsToListen() {

  }

  showOwnedAlbums() {

  }

  showSoughtAlbums() {

  }
}
