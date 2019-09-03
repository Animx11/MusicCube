import { Component, OnInit } from '@angular/core';

import { SongService } from 'src/app/Services/song.service';

import { Song } from 'src/app/Class/Song';
import { SongAuthorship } from 'src/app/Class/SongAuthorship';
import { SongInstrument } from 'src/app/Class/SongInstrument';
import { SongAuthorshipService } from 'src/app/Services/song-authorship.service';
import { SongInstrumentService } from 'src/app/Services/song-instrument.service';

@Component({
  selector: 'app-edit-song',
  templateUrl: './edit-song.component.html',
  styleUrls: ['./edit-song.component.css']
})
export class EditSongComponent implements OnInit {
  private selectedSong: Song;

  private songName: string;
  private songLengthSeconds: number;
  private trackNumber: number;
  private song: Song;

  private authorList: SongAuthorship[];
  private authorship: SongAuthorship;

  private instrumentList: SongInstrument[];
  private songInstrument: SongInstrument;

  private isBandClicked: boolean;
  private isAlbumClicked: boolean;
  private isGenreClicked: boolean;
  private isAuthorClicked: boolean;
  private isInstrumentClicked: boolean;

  private isBandSelected: boolean;
  private isAlbumSelected: boolean;
  private isGenreSelected: boolean;


  private isEditSelected: boolean;
  private isCountryClicked: boolean;
  private isCountrySelected: boolean;

  private isInstrumentChanged: boolean;
  private isAuthorshipChanged: boolean;
  


  private minutes: number;
  private seconds: number;
  private toMandS: number;

  constructor(private songService: SongService,
    private songAuthorshipService: SongAuthorshipService,
    private songInstrumentService: SongInstrumentService) {}

  ngOnInit() {
    this.songName = '';
    this.song = new Song();
    this.authorList = [];
    this.instrumentList = [];
    this.trackNumber = null;
    this.isAuthorshipChanged = false;
    this.isInstrumentChanged = false;
    
    this.isEditSelected = false;

    this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
    this.isBandSelected = this.isAlbumSelected = this.isGenreSelected = false;
  }

  songEventHandler($event) {
    this.selectedSong = $event;
    this.songName = this.selectedSong.songName;
    this.trackNumber = this.selectedSong.trackNumber;
    this.songLengthSeconds = this.selectedSong.songLengthSeconds;
    this.song.setAlbum(this.selectedSong.album);
    this.song.setBand(this.selectedSong.band);
    this.song.setGenre(this.selectedSong.genre);
    if (this.song.getAlbum()){
      this.isAlbumSelected = true;
    }
    if (this.song.getBand()){
      this.isBandSelected = true;
    }
    if (this.song.getGenre()){
      this.isGenreSelected = true;
    }
    this.songAuthorshipService.getBySongId(this.selectedSong.id).subscribe(
      res => {
        this.authorList = res.map(el => new SongAuthorship(el));
      }
    );
    this.songInstrumentService.getBySongId(this.selectedSong.id).subscribe(
      res => {
        this.instrumentList = res.map(el => new SongInstrument(el));
      }
    );
  this.lengthInSecondsTodMinutesAndSeconds();

    this.isEditSelected = true;
    this.isCountryClicked = false;
    this.isCountrySelected = false;

  }
  searchEventHandler($event) {
    this.selectedSong = null;
  }



  albumLengthInSeconds(): boolean {
    if(this.minutes < 0 || this.seconds < 0 || this.seconds > 59 || this.minutes === 0 && this.seconds === 0){
      return false;
    }
    else{
      this.songLengthSeconds = 60 * this.minutes + this.seconds;
      return true;
    }
  }

  lengthInSecondsTodMinutesAndSeconds(){
      this.toMandS = this.songLengthSeconds * 100 / 60;
      this.minutes = (this.toMandS - (this.toMandS % 100)) / 100;
      this.seconds = ((this.toMandS % 100) - (this.toMandS % 1)) * 60 / 100;
      this.seconds = this.seconds - (this.seconds % 1);

  }






  
  resetClicked(){
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
    this.isAuthorshipChanged = true;

  }
  instrumentEventHandler($event: any) {
    this.songInstrument = new SongInstrument();
    this.songInstrument.setInstrument($event);
    this.instrumentList.push(this.songInstrument);
    console.log($event);
    console.log(this.instrumentList);
    this.isInstrumentClicked = false;
    this.isInstrumentChanged = true;

  }

  removeAuthor(authorship: SongAuthorship) {
    const index = this.authorList.indexOf(authorship);
    if (index > -1) { this.authorList.splice(index, 1); }
    this.isAuthorshipChanged = true;
  }

  removeInstrument(instrument: SongInstrument) {
    const index = this.instrumentList.indexOf(instrument);
    if (index > -1) { this.instrumentList.splice(index, 1); }
    this.isInstrumentChanged = true;
  }



  delete() {
    this.songService.delete(this.selectedSong.id).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occured');
      }
    );
  }
  
  update() {
    this.albumLengthInSeconds();
    if(this.songName === this.selectedSong.songName && this.songLengthSeconds === this.selectedSong.songLengthSeconds && this.trackNumber === this.selectedSong.trackNumber && this.songLengthSeconds === this.selectedSong.songLengthSeconds && this.song.getAlbum() === this.selectedSong.album && this.song.getBand() === this.selectedSong.band && this.song.getGenre() === this.selectedSong.genre && !this.isAuthorshipChanged && !this.isInstrumentChanged) {
      window.alert('You need to do some changes before update');
    } else {
      this.song.id = this.selectedSong.id;
      this.song.setSongName(this.songName);
      this.song.setTrackNumber(this.trackNumber);
      this.song.setSongLengthSeconds(this.songLengthSeconds);

      this.authorList.forEach(el => {
        el.setSong(this.song);
        this.songAuthorshipService.edit(el).subscribe(
          res => {
            console.log('add-song-component received:');
            console.log(res);
          },
          err => {
            console.error(err);
          }
        );
      });

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

      this.songService.edit(this.song).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occured');
        }
      );
    }
  }
  
  reset() {
    this.isInstrumentChanged = false;
    this.isAuthorshipChanged = false;
    this.isEditSelected = false;
    this.selectedSong = null;
    this.song = new Song();
    this.songName = '';
    this.minutes = 0;
    this.seconds = 0;
    this.songLengthSeconds = 0;
    this.authorList = [];
    this.instrumentList = [];
    this.trackNumber = null;
    this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
    this.isBandSelected = this.isAlbumSelected = this.isGenreSelected = false;
  }

}
