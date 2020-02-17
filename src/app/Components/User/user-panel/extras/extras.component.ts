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

  userName: string;

  constructor(
    private userAlbumService: UserAlbumStatusService,
    private userSongService: UserSongStatusService,
    private tokenService: TokenStorageService
  ) { }

  ngOnInit() {
    this.userName = this.tokenService.getUsername();
  }

  reset() {
    this.isOwnedAlbumsClicked =
    this.isSoughtAlbumsClicked =
    this.isYourSongsClicked =
    this.isToListenClicked = false;
  }

  showListenedSongs() {
    this.reset();
    this.isToListenClicked = true;
    this.userSongService.getListened(this.userName).subscribe(
      res => {
        console.log('Extras component received songRatingDtos', res);
        this.songList = res.map(el => new Song(el.song));
      },
      error1 => console.error(error1)
    );
  }

  showSongsToListen() {
    this.reset();
    this.isToListenClicked = true;
    this.userSongService.getToListen(this.userName).subscribe(
      res => {
        console.log('Extras component received songRatingDtos', res);
        this.songList = res.map(el => new Song(el.song));
      },
      error1 => console.error(error1)
    );
  }

  showOwnedAlbums() {
    this.reset();
    this.isOwnedAlbumsClicked = true;
    this.userAlbumService.getOwned(this.userName).subscribe(
      res => {
        console.log('Extras component received albums', res);
        this.albumList = res.map(el => new Album(el.album));
      },
      error1 => console.error(error1)
    );

  }

  showSoughtAlbums() {
    this.reset();
    this.isSoughtAlbumsClicked = true;
    this.userAlbumService.getSought(this.userName).subscribe(
      res => {
        console.log('Extras component received albums', res);
        this.albumList = res.map(el => new Album(el.album));
        },
      error1 => console.error(error1)
    );
  }
}
