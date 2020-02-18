import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Song} from '../../../Class/Song';
import {SongService} from '../../../Services/song.service';

@Component({
  selector: 'app-advanced-song',
  templateUrl: './advanced-song.component.html',
  styleUrls: ['./advanced-song.component.css']
})
export class AdvancedSongComponent implements OnInit {

  titles: string;
  noTitles: string;
  maxMin: number;
  maxSec: number;
  minMin: number;
  minSec: number;
  bands: string;
  noBands: string;
  genres: string;
  noGenres: string;
  instruments: string;
  noInstruments: string;

  newTitle: string;
  newNoTitle: string;
  newBand: string;
  newNoBand: string;
  newGenre: string;
  newNoGenre: string;
  newInstrument: string;
  newNoInstrument: string;

  constructor(private service: SongService) {
    this.titles = this.noTitles = this.bands = this.noBands = this.genres = this.noGenres = this.instruments = this.noInstruments = '';
    this.newTitle = this.newNoTitle = this.newBand = this.newNoBand = this.newGenre = this.newNoGenre = this.newInstrument = this.newNoInstrument = '';
  }
  @Output() songSearchEvent = new EventEmitter<Song[]>();
  ngOnInit() {

  }

  addBand() {
    this.bands += (`${this.newBand},`);
    this.newBand = '';
  }

  addTitle() {
    this.titles += (`${this.newTitle},`);
    this.newTitle = '';
  }

  addNoTitle() {
    this.noTitles += (`${this.newNoTitle},`);
    this.newNoTitle = '';
  }

  addNoBand() {
    this.noBands += (`${this.newNoBand},`);
    this.newNoBand = '';
  }

  addGenre() {
    this.genres += (`${this.newGenre},`);
    this.newGenre = '';
  }

  addNoGenre() {
    this.noGenres += (`${this.newNoGenre},`);
    this.newNoGenre = '';
  }

  addInstrument() {
    this.instruments += (`${this.newInstrument},`);
    this.newInstrument = '';
  }

  addNoInstrument() {
    this.noInstruments += (`${this.newNoInstrument},`);
    this.newNoInstrument = '';
  }

  go() {
    let params: Map<string, string>;
    params = new Map<string, string>();
    if (this.titles) {
      params.set('title', this.titles.slice(0, this.titles.length - 1));
    }
    if (this.noTitles) {
      params.set('notitle', this.noTitles.slice(0, this.titles.length - 1));
    }
    if (this.maxMin || this.maxSec) {
      params.set('maxlen', (((this.maxMin ? this.maxMin : 0) * 60) + (this.maxSec ? this.maxSec : 0)).toString());
    }
    if (this.minMin || this.minSec) {
      params.set('minlen', (((this.minMin ? this.minMin : 0) * 60) + (this.minSec ? this.minSec : 0)).toString());
    }
    if (this.bands) {
      params.set('band', this.bands.slice(0, this.bands.length - 1));
    }
    if (this.noBands) {
      params.set('noband', this.noBands.slice(0, this.bands.length - 1));
    }
    if (this.genres) {
      params.set('genre', this.genres.slice(0, this.genres.length - 1));
    }
    if (this.noGenres) {
      params.set('nogenre', this.noGenres.slice(0, this.genres.length - 1));
    }
    if (this.instruments) {
      params.set('instrument', this.instruments.slice(0, this.instruments.length - 1));
    }
    if (this.noInstruments) {
      params.set('noinstrument', this.noInstruments.slice(0, this.instruments.length - 1));
    }

    this.service.advanced(params).subscribe(res => {
      console.log('advanced song search results:', res);
    });
  }
}
