import { Component, OnInit } from "@angular/core";
import { SongService } from "src/app/Services/song.service";
import {
  HttpClient,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse,
} from "@angular/common/http";
import { Song } from "src/app/Class/Song";

@Component({
  selector: "app-edit-song",
  templateUrl: "./edit-song.component.html",
  styleUrls: ["./edit-song.component.css"],
})
export class EditSongComponent implements OnInit {
  constructor(private songService: SongService) {}

  songName: string;
  songs: Song[];

  ngOnInit() {
    this.songName = "";
  }

  findBySongName() {
    this.songService.getBySongName(this.songName).subscribe(res => {
      console.log(res);
    });
  }

  findAllSongs() {
    this.songService.list().subscribe(res => {
      console.log(res);
      this.songs = res.map(el => new Song(el));
    });
  }
}
