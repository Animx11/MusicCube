import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { SongService } from "src/app/Services/song.service";

import { Song } from "src/app/Class/Song";
import { Band } from "src/app/Class/Band";
import { Album } from "src/app/Class/Album";
import { Genre } from "src/app/Class/Genre";

@Component({
  selector: "app-add-song",
  templateUrl: "./add-song.component.html",
  styleUrls: ["./add-song.component.css"]
})
export class AddSongComponent implements OnInit {
  private songName: string;
  private songLengthSeconds: number;
  private song: Song;

  constructor(private songService: SongService) {}

  ngOnInit() {
    this.songName = "";
    this.song = new Song();
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
          window.alert("Dodano nową piosenke");
        },
        err => {
          if (err.status === 409) {
            window.alert("Podana piosenkam istnieje w bazie danych");
          } else {
            window.alert("Nie udało połączyć się z serwerem");
          }
        }
      );
    }
  }
}
