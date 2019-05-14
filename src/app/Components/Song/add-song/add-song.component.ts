import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { SongService } from "src/app/Services/song.service";
import { BandService } from "src/app/Services/band.service";
import { AlbumService } from "src/app/Services/album.service";
import { GenreService } from "src/app/Services/genre.service";

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
  private album: Album;
  private band: Band;
  private genre: Genre;

  private albumsSelected: boolean;
  private bandsSelected: boolean;
  private genresSelected: boolean;

  private bandList: Band[];
  private albumList: Album[];
  private genreList: Genre[];

  constructor(
    private songService: SongService,
    private bandService: BandService,
    private albumService: AlbumService,
    private genreService: GenreService
  ) {}

  ngOnInit() {
    this.songName = "";
    this.album = null;
    this.band = null;
    this.genre = null;
    this.song = new Song();

    this.bandsSelected = this.albumsSelected = this.genresSelected = false;
  }

  selectBand() {
    this.albumsSelected = this.genresSelected = false;
    this.bandService.list().subscribe(res => {
      console.log("add-sonng-component recieved:");
      console.log(res);
      this.bandList = res.map(el => new Band(el));
    });
    this.bandsSelected = true;
  }
  selectAlbum() {
    this.bandsSelected = this.genresSelected = false;
    this.albumService.list().subscribe(res => {
      console.log("add-sonng-component recieved:");
      console.log(res);
      this.albumList = res.map(el => new Album(el));
    });
    this.albumsSelected = true;
  }

  selectGenre() {
    this.albumsSelected = this.bandsSelected = false;
    this.genreService.list().subscribe(res => {
      console.log("add-sonng-component recieved:");
      console.log(res);
      this.genreList = res.map(el => new Genre(el));
    });
    this.genresSelected = true;
  }

  addBand(band: Band) {
    this.song.setBand(band);
    this.bandsSelected = this.albumsSelected = this.genresSelected = false;
  }
  addAlbum(album: Album) {
    this.song.setAlbum(album);
    this.bandsSelected = this.albumsSelected = this.genresSelected = false;
  }

  addGenre(genre: Genre) {
    this.song.setGenre(genre);
    this.bandsSelected = this.albumsSelected = this.genresSelected = false;
  }

  addSong() {
    if (this.songName === "") {
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
