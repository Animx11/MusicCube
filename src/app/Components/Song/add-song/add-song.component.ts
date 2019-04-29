import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import {
  HttpClient,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse,
} from "@angular/common/http";
import { SongService } from "src/app/Services/song.service";
import { Song } from "src/app/Class/Song";

@Component({
  selector: "app-add-song",
  templateUrl: "./add-song.component.html",
  styleUrls: ["./add-song.component.css"],
})
export class AddSongComponent implements OnInit {
  songName: String;
  songLengthSeconds: number;
  addNewSong: Song;

  constructor(private songService: SongService) {}

  ngOnInit() {
    this.songName = "";
    this.songLengthSeconds = 0;
    this.album = null;
    this.band = null;
    this.genre = null;
    this.addNewSong = new Song();
  }
  f;

  addSong() {
    if (this.songName == "") {
      window.alert("Dane są niekompletne i/lub nieprawidłowe");
    } else {
      this.addNewSong.setSongName(this.songName);
      this.addNewSong.setSongLengthSeconds(this.songLengthSeconds);

      this.songService.create(this.addNewSong).subscribe(
        res => {
          console.log(res);
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
}
