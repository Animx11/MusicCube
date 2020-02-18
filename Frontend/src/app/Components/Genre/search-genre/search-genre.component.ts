import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { GenreService } from 'src/app/Services/genre.service';
import { Genre } from 'src/app/Class/Genre';

@Component({
  selector: 'app-search-genre',
  templateUrl: './search-genre.component.html',
  styleUrls: ['./search-genre.component.css']
})
export class SearchGenreComponent implements OnInit {
  searchOn: boolean;

  genres$: Observable<Genre[]>;
  searchTerms = new Subject<string>();

  @Output() genreEvent = new EventEmitter<Genre>();
  @Output() searchEvent = new EventEmitter();

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
    this.genres$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.genreService.getByGenreName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
      this.searchTerms.next(term);
    }
  }

  onSelect(genre: Genre) {
    this.genreEvent.emit(genre);
  }
}
