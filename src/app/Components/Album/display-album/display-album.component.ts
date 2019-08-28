import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { AlbumService } from '../../../Services/album.service';
import {Album} from '../../../Class/Album';
import {Band} from '../../../Class/Band';
import {Song} from '../../../Class/Song';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';

@Component({
  selector: 'app-display-album',
  templateUrl: './display-album.component.html',
  styleUrls: ['./display-album.component.css']
})
export class DisplayAlbumComponent implements OnInit {

  album: Album;
  bands: Band[];
  songs: Song[];

  private isLogged: boolean;
  private isFavorite: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private albumService: AlbumService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService) { }

  ngOnInit() {
    this.getAlbum();
    this.isLogged = false;
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.checkIfIsFavorite();
    }
  }

  private checkIfIsFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.favoriteListsService.existAlbumInUserFavorites(this.tokenStorage.getUsername(), id).subscribe(
      res => {
        this.isFavorite = res;
      },
      err => {
        window.alert(err);
      }
    );
  }

  private getAlbum() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.albumService.getById(id).subscribe(
      res => {
        this.album = new Album(res);
        console.log('display-album-component received: ', res);
        this.getBands();
        this.getSongs();
      },
      err => console.error(err));
  }
  private getBands() {
    this.albumService.getAlbumBands(this.album.getId()).subscribe(res => {
      console.log('display-album-component received: ', res);
      this.bands = res.map(el => new Band(el));
    },
    err => console.error(err));
  }
  private getSongs() {
    this.albumService.getAlbumSongs(this.album.getId()).subscribe(res => {
      console.log('display-album-component received: ', res);
      this.songs = res.map(el => new Song(el));
    },
    err => console.error(err));
  }

  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if(this.isFavorite) {
      this.favoriteListsService.deleteAlbumToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Album succesfully deleted from favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else {
      this.favoriteListsService.addAlbumToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Album succesfully added to favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    }
  }

}
