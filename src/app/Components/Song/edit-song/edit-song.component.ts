import { Component, OnInit } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";

import { SongService } from "src/app/Services/song.service";

import { Song } from "src/app/Class/Song";

@Component({
  selector: "app-edit-song",
  templateUrl: "./edit-song.component.html",
  styleUrls: ["./edit-song.component.css"],
})
export class EditSongComponent implements OnInit {
  private songs$: Observable<Song[]>;
  private searchTerms = new Subject<string>();
  private selectedSong: Song;

  constructor(private songService: SongService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(song: Song): void {
    this.selectedSong = song;
  }

  ngOnInit() {
    this.songs$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
  }

  // findBySongName() {
  //   this.songService.getBySongName(this.songName).subscribe(res => {
  //     console.log(res);
  //   });
  // }

  // findAllSongs() {
  //   this.songService.list().subscribe(res => {
  //     console.log(res);
  //     this.songs = res.map(el => new Song(el));
  //   });
  // }
}
