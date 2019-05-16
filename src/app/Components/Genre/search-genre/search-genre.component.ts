import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { GenreService } from "src/app/Services/genre.service";
import { Genre } from "src/app/Class/Genre";

@Component({
  selector: "app-search-genre",
  templateUrl: "./search-genre.component.html",
  styleUrls: ["./search-genre.component.css"]
})
export class SearchGenreComponent implements OnInit {
  private searchOn: boolean;

  genres$: Observable<Genre[]>;
  private searchTerms = new Subject<string>();

  @Output() genreEvent = new EventEmitter<Genre>();

  constructor(private genreService: GenreService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.genres$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.genreService.getByGenreName(term))
    );
  }

  toggleSearch() {
    this.searchOn = !this.searchOn;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(genre: Genre) {
    this.toggleSearch();
    this.genreEvent.emit(genre);
  }
}
