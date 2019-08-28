import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { SongService } from '../../../Services/song.service';
import {Song} from '../../../Class/Song';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';

@Component({
  selector: 'app-display-song',
  templateUrl: './display-song.component.html',
  styleUrls: ['./display-song.component.css']
})
export class DisplaySongComponent implements OnInit {

  song: Song;

  private isLogged: boolean;
  private isFavorite: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private songService: SongService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService) { }

  ngOnInit() {
    this.getSong();
    this.isLogged = false;
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.checkIfIsFavorite();
    }
  }

  private checkIfIsFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.favoriteListsService.existSongInUserFavorites(this.tokenStorage.getUsername(), id).subscribe(
      res => {
        this.isFavorite = res;
      },
      err => {
        window.alert(err);
      }
    );
  }

  private getSong() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.songService.getById(id).subscribe(
      res => {
        this.song = new Song(res);
        console.log('display-song-component received: ', res);
      },
        err => console.error(err));
  }

  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if(this.isFavorite) {
      this.favoriteListsService.deleteSongToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Song succesfully deleted from favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else {
      this.favoriteListsService.addSongToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Song succesfully added to favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    }
  }
}
