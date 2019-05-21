import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { SongService } from "src/app/Services/song.service";
import { SongAuthorshipService } from "src/app/Services/song-authorship.service";

import { Song } from "src/app/Class/Song";
import { Band } from "src/app/Class/Band";
import { Album } from "src/app/Class/Album";
import { Genre } from "src/app/Class/Genre";
import { Person } from "src/app/Class/Person";
import { SongAuthorship } from "src/app/Class/SongAuthorship";
import { Instrument } from "src/app/Class/Instrument";
import { SongInstrument } from "src/app/Class/SongInstrument";

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
    private songAuthorshipService: SongAuthorshipService
  ) {}

  ngOnInit() {
    this.songName = "";
    this.song = new Song();
    this.authorList = [];
    this.instrumentList = [];
  }

  bandEventHander($event: any) {
    this.song.setBand($event);
  }
  albumEventHander($event: any) {
    this.song.setAlbum($event);
  }
  genreEventHander($event: any) {
    this.song.setGenre($event);
  }
  personEventHander($event: any) {
    this.authorship = new SongAuthorship();
    this.authorship.setAuthor($event);
    this.authorList.push(this.authorship);
  }
  instrumentEventHander($event: any) {
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
          window.alert("Dodano nową piosenke");
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
  toggleWroteText(authorship: SongAuthorship) {
    authorship.setWroteText(!authorship.getWroteText());
  }
  toggleWroteMusic(authorship: SongAuthorship) {
    authorship.setWroteMusic(!authorship.getWroteMusic());
  }
  removeAuthor(authorship: SongAuthorship) {
    let index = this.authorList.indexOf(authorship);
    if (index > -1) this.authorList.splice(index, 1);
  }
}
