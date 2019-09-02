import { Component, OnInit, Input } from '@angular/core';

import { SongService } from 'src/app/Services/song.service';
import { SongAuthorshipService } from 'src/app/Services/song-authorship.service';
import { SongInstrumentService } from 'src/app/Services/song-instrument.service';

import { Song } from 'src/app/Class/Song';
import { SongAuthorship } from 'src/app/Class/SongAuthorship';
import { SongInstrument } from 'src/app/Class/SongInstrument';


@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})
export class AddSongComponent implements OnInit {
  songName: string;
  songLengthSeconds: number;
  song: Song;
  authorList: SongAuthorship[];
  authorship: SongAuthorship;
  instrumentList: SongInstrument[];
  songInstrument: SongInstrument;
  minutes: number;
  seconds: number;
  trackNumber: number;

  isBandClicked: boolean;
  isAlbumClicked: boolean;
  isGenreClicked: boolean;
  isAuthorClicked: boolean;
  isInstrumentClicked: boolean;

  isBandSelected: boolean;
  isAlbumSelected: boolean;
  isGenreSelected: boolean;



  constructor(
    private songService: SongService,
    private songAuthorshipService: SongAuthorshipService,
    private songInstrumentService: SongInstrumentService
  ) {}

  ngOnInit() {
    this.songName = '';
    this.song = new Song();
    this.authorList = [];
    this.instrumentList = [];


    this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
    this.isBandSelected = this.isAlbumSelected = this.isGenreSelected = false;
  }

  resetClicked() {
    this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
  }

  searchValue(value: boolean): boolean {
    this.resetClicked();
    return !value;
  }

  bandEventHandler($event: any) {
    this.song.setBand($event);
    this.isBandSelected = true;
    this.isBandClicked = false;
  }
  albumEventHandler($event: any) {
    this.song.setAlbum($event);
    this.isAlbumSelected = true;
    this.isAlbumClicked = false;
  }
  genreEventHandler($event: any) {
    this.song.setGenre($event);
    this.isGenreSelected = true;
    this.isGenreClicked = false;
  }
  personEventHandler($event: any) {
    this.authorship = new SongAuthorship();
    this.authorship.setAuthor($event);
    this.authorList.push(this.authorship);
    this.isAuthorClicked = false;

  }
  instrumentEventHandler($event: any) {
    this.songInstrument = new SongInstrument();
    this.songInstrument.setInstrument($event);
    this.instrumentList.push(this.songInstrument);
    console.log($event);
    console.log(this.instrumentList);
    this.isInstrumentClicked = false;

  }

  addSong() {
    if (this.songName === '' || this.songLengthSeconds === 0) {
      window.alert('Dane są niekompletne i/lub nieprawidłowe');
    } else {
      this.song.setSongName(this.songName);
      this.song.setSongLengthSeconds(this.songLengthSeconds);

      this.songService.create(this.song).subscribe(
        res => {
          console.log('add-song-component received:');
          console.log(res);
          this.song = new Song(res);
          // DODAWANIE AUTORÓW
          this.authorList.forEach(el => {
            el.setSong(this.song);
            this.songAuthorshipService.create(el).subscribe(
              res => {
                console.log('add-song-component received:');
                console.log(res);
              },
              err => {
                console.error(err);
              }
            );
          });
          // DODAWANIE INSTRUMENTÓW
          this.instrumentList.forEach(el => {
            el.setSong(this.song);
            this.songInstrumentService.create(el).subscribe(
              res => {
                console.log('add-song-component received:');
                console.log(res);
              },
              err => console.error(err)
            );
          });
          window.alert('Dodano nową piosenke');
          this.ngOnInit();
          // window.location.assign(`${thisURL}AdminPanel`);
        },
        err => {
          if (err.status === 409) {
            window.alert('Podana piosenkam istnieje w bazie danych');
          } else {
            window.alert('Nie udało połączyć się z serwerem');
          }
        }
      );
    }
  }
  removeAuthor(authorship: SongAuthorship) {
    const index = this.authorList.indexOf(authorship);
    if (index > -1) { this.authorList.splice(index, 1); }
  }

  removeInstrument(instrument: SongInstrument) {
    const index = this.instrumentList.indexOf(instrument);
    if (index > -1) { this.instrumentList.splice(index, 1); }
  }
}
