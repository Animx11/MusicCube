import { Component, OnInit, Input } from "@angular/core";

import { SongService } from "src/app/Services/song.service";
import { SongAuthorshipService } from "src/app/Services/song-authorship.service";
import { SongInstrumentService } from "src/app/Services/song-instrument.service";

import { Song } from "src/app/Class/Song";
import { Band } from "src/app/Class/Band";
import { Album } from "src/app/Class/Album";
import { Genre } from "src/app/Class/Genre";
import { Person } from "src/app/Class/Person";
import { SongAuthorship } from "src/app/Class/SongAuthorship";
import { Instrument } from "src/app/Class/Instrument";
import { SongInstrument } from "src/app/Class/SongInstrument";

// import { this_url } from "src/app/Services/API_URL";
// const thisURL = this_url;

@Component({
  selector: "app-add-song",
  templateUrl: "./add-song.component.html",
  styleUrls: ["./add-song.component.css"]
})
export class AddSongComponent implements OnInit {
  private songName: string;
  private songLengthSeconds: number;
  private song: Song;
  private authorList: SongAuthorship[];
  private authorship: SongAuthorship;
  private instrumentList: SongInstrument[];
  private songInstrument: SongInstrument;

  constructor(
    private songService: SongService,
    private songAuthorshipService: SongAuthorshipService,
    private songInstrumentService: SongInstrumentService
  ) {}

  ngOnInit() {
    this.songName = "";
    this.song = new Song();
    this.authorList = [];
    this.instrumentList = [];
  }

  bandEventHandler($event: any) {
    this.song.setBand($event);
  }
  albumEventHandler($event: any) {
    this.song.setAlbum($event);
  }
  genreEventHandler($event: any) {
    this.song.setGenre($event);
  }
  personEventHandler($event: any) {
    this.authorship = new SongAuthorship();
    this.authorship.setAuthor($event);
    this.authorList.push(this.authorship);
  }
  instrumentEventHandler($event: any) {
    this.songInstrument = new SongInstrument();
    this.songInstrument.setInstrument($event);
    this.instrumentList.push(this.songInstrument);
    console.log($event);
    console.log(this.instrumentList);
  }

  addSong() {
    if (this.songName === "" || this.songLengthSeconds === 0) {
      window.alert("Dane są niekompletne i/lub nieprawidłowe");
    } else {
      this.song.setSongName(this.songName);
      this.song.setSongLengthSeconds(this.songLengthSeconds);

      this.songService.create(this.song).subscribe(
        res => {
          console.log("add-song-component recieved:");
          console.log(res);
          this.song = new Song(res);
          // DODAWANIE AUTORÓW
          this.authorList.forEach(el => {
            el.setSong(this.song);
            this.songAuthorshipService.create(el).subscribe(
              res => {
                console.log("add-song-component recieved:");
                console.log(res);
              },
              err => {
                console.log(err);
              }
            );
          });
          // DODAWANIE INSTRUMENTÓW
          this.instrumentList.forEach(el => {
            el.setSong(this.song);
            this.songInstrumentService.create(el).subscribe(
              res => {
                console.log("add-song-component recieved:");
                console.log(res);
              },
              err => console.log(err)
            );
          });
          window.alert("Dodano nową piosenke");
          //window.location.assign(`${thisURL}AdminPanel`);
        },
        err => {
          if (err.status == 409) {
            window.alert("Podana piosenkam istnieje w bazie danych");
          } else {
            window.alert("Nie udało połączyć się z serwerem");
          }
        }
      );
    }
  }
  removeAuthor(authorship: SongAuthorship) {
    let index = this.authorList.indexOf(authorship);
    if (index > -1) this.authorList.splice(index, 1);
  }
}
