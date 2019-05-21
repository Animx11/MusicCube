import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { SongService } from "src/app/Services/song.service";
import { Song } from "src/app/Class/Song";

@Component({
  selector: "app-search-song",
  templateUrl: "./search-song.component.html",
  styleUrls: ["./search-song.component.css"]
})
export class SearchSongComponent implements OnInit {
  private searchOn: boolean;
  private listOn: boolean;

  songs$: Observable<Song[]>;
  private searchTerms = new Subject<string>();

  @Output() songEvent = new EventEmitter<Song>();

  constructor(private songService: SongService) {
    this.searchOn = false;
    this.listOn = true;
  }

  ngOnInit(): void {
    this.songs$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
  }

  toggleSearch() {
    this.songs$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
    this.searchOn = !this.searchOn;
    this.listOn = true;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(song: Song) {
    this.searchOn = false;
    this.listOn = false;
    this.songEvent.emit(song);
  }

  getAll() {
    this.searchOn = false;
    this.listOn = true;
    this.songs$ = this.songService.list();
  }
}
