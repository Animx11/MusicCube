import { Component, OnInit } from '@angular/core';
import {Artist} from '../../../Class/Artist';
import {Album} from '../../../Class/Album';
import {Band} from '../../../Class/Band';
import {Song} from '../../../Class/Song';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css']
})
export class AdvancedSearchComponent implements OnInit {

  artists: boolean;
  bands: boolean;
  albums: boolean;
  songs: boolean;
  genres: boolean;
  instruments: boolean;

  foundArtists: Artist[];
  foundBands: Band[];
  foundAlbums: Album[];
  foundSongs: Song[];

  constructor() {
    this.songs = true;
  }

  ngOnInit() {
  }

  selectCat(category: string) {
    switch (category) {
      case 'artists':
        this.artists = true;
        this.bands = this.albums = this.songs = this.genres = this.instruments = false;
        break;
      case 'bands':
        this.bands = true;
        this.artists = this.albums = this.songs = this.genres = this.instruments = false;
        break;
      case 'albums':
        this.albums = true;
        this.bands = this.artists = this.songs = this.genres = this.instruments = false;
        break;
      case 'songs':
        this.songs = true;
        this.bands = this.albums = this.artists = this.genres = this.instruments = false;
        break;
    }
  }

  artistSearchHandler($event) {
    this.foundArtists = $event;
  }
  bandSearchHandler($event) {
    this.foundBands = $event;
  }
  albumSearchHandler($event) {
    this.foundAlbums = $event;
  }
  songSearchHandler($event) {
    this.foundSongs = $event;
  }
  noResultEventHandler($event) {
    window.alert(`No ${$event} found`);
  }
  clearHandler($event) {
    this.foundSongs = this.foundArtists = this.foundAlbums = this.foundBands = [];
  }

}
