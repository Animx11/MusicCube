import { Component, OnInit } from '@angular/core';
import {Artist} from '../../../Class/Artist';
import {Album} from '../../../Class/Album';
import {Band} from '../../../Class/Band';
import {Genre} from '../../../Class/Genre';
import {Song} from '../../../Class/Song';
import {Instrument} from '../../../Class/Instrument';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css']
})
export class AdvancedSearchComponent implements OnInit {

  private artists: boolean;
  private bands: boolean;
  private albums: boolean;
  private songs: boolean;
  private genres: boolean;
  private instruments: boolean;

  private foundArtists: Artist[];
  private foundBands: Band[];
  private foundAlbums: Album[]
  private foundSongs: Song[];
  private foundGenres: Genre[];
  private foundInstruments: Instrument[];

  constructor() {
    this.artists = true;
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
      case 'genres':
        this.genres = true;
        this.bands = this.albums = this.songs = this.artists = this.instruments = false;
        break;
      case 'instruments':
        this.instruments = true;
        this.bands = this.albums = this.songs = this.genres = this.artists = false;
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
  genreSearchHandler($event) {
    this.foundGenres = $event;
  }
  instrumentSearchHandler($event) {
    this.foundInstruments = $event;
  }
  noResultEventHandler($event) {
    window.alert(`No ${$event} found`);
  }

}
