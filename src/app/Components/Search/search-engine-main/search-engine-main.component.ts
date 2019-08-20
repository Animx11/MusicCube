import {Component, OnInit} from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { BandService } from 'src/app/Services/band.service';
import { AlbumService } from 'src/app/Services/album.service';
import { SongService } from 'src/app/Services/song.service';
import { GenreService } from 'src/app/Services/genre.service';
import { InstrumentService } from 'src/app/Services/instrument.service';


import { Band } from 'src/app/Class/Band';
import { Album } from 'src/app/Class/Album';
import { Song } from 'src/app/Class/Song';
import { Genre } from 'src/app/Class/Genre';
import { Instrument } from 'src/app/Class/Instrument';
import {Artist} from '../../../Class/Artist';
import {ArtistService} from '../../../Services/artist.service';


@Component({
  selector: 'app-search-engine-main',
  templateUrl: './search-engine-main.component.html',
  styleUrls: ['./search-engine-main.component.css']
})
export class SearchEngineMainComponent implements OnInit {

  artists: Artist[];
  bands: Band[];
  albums: Album[];
  songs: Song[];
  genres: Genre[];
  instruments: Instrument[];

  private switches = [];

  constructor(
    private artistService: ArtistService,
    private bandService: BandService,
    private albumService: AlbumService,
    private songService: SongService,
    private genreService: GenreService,
    private instrumentService: InstrumentService) {
    this.switches[0] = true; // all
    this.switches[1] = true; // band
    this.switches[2] = true; // artist
    this.switches[3] = true; // album
    this.switches[4] = true; // song
    this.switches[5] = true; // instrument
    this.switches[6] = true; // genre
  }

  ngOnInit() {
  }

  search(term: string): void {
    if (this.switches[1]) this.searchBands(term);
    if (this.switches[2]) this.searchArtists(term);
    if (this.switches[3]) this.searchAlbums(term);
    if (this.switches[4]) this.searchSongs(term);
    if (this.switches[5]) this.searchInstruments(term);
    if (this.switches[6]) this.searchGenres(term);

  }

  switchAll() {
    this.massSet(!this.switches[0]);
    document.getElementById('all-check-lbl').innerText = this.switches[0] ? 'Disable all' : 'Enable all';
  }

  switchCheck(nr: number) {
    let empty = true;
    let full = true;
    for (let i = 1; i < 7; i++) {
      if (i !== nr) {
        if (this.switches[i]) {
          empty = false;
        } else {
          full = false;
        }
      }
    }
    if (empty) {
      this.switches[0] = false;
      document.getElementById('all-check-lbl').innerText = 'Enable all';
    }
    if (full) {
      this.switches[0] = true;
      document.getElementById('all-check-lbl').innerText = 'Disable all';
    }
  }

  massSet(b: boolean) {
    for (const i in this.switches) {
      this.switches[i] = b;
    }
  }
  searchBands(term: string) {
    this.bandService.getByBandName(term).subscribe(res => {
      console.log('search-engine received bands:', res);
      this.bands = res.map(el => new Band(el));
    });
  }
  searchAlbums(term: string) {
    this.albumService.getByAlbumName(term).subscribe(res => {
      console.log('search-engine received albums:', res);
      this.albums = res.map(el => new Album(el));
    });
  }
  searchArtists(term: string) {
    this.artistService.getByStageName(term).subscribe(res => {
      console.log('search-engine received artists:', res);
      this.artists = res.map(el => new Artist(el));
    });
  }
  searchSongs(term: string) {
    this.songService.getBySongName(term).subscribe(res => {
      console.log('search-engine received songs:', res);
      this.songs = res.map(el => new Song(el));
    });
  }
  searchGenres(term: string) {
    this.genreService.getByGenreName(term).subscribe(res => {
      console.log('search-engine received genres:', res);
      this.genres = res.map(el => new Genre(el));
    });
  }
  searchInstruments(term: string) {
    this.instrumentService.getByInstrumentName(term).subscribe(res => {
      console.log('search-engine received instruments:', res);
      this.instruments = res.map(el => new Instrument(el));
    });
  }
}
