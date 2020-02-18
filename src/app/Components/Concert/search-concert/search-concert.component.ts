import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Concert } from 'src/app/Class/Concert';
import { Observable, Subject } from 'rxjs';
import { ConcertService } from 'src/app/Services/concert.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-search-concert',
  templateUrl: './search-concert.component.html',
  styleUrls: ['./search-concert.component.css']
})
export class SearchConcertComponent implements OnInit {

  searchOn: boolean;
  listOn: boolean;
  reachedLastPage: boolean;
  pageCount: number;

  concerts$: Observable<Concert[]>;
  concertPage: Concert[];
  searchTerms = new Subject<string>();

  @Output() concertEvent = new EventEmitter<Concert>();
  @Output() searchEvent = new EventEmitter();

  constructor(private concertService: ConcertService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.concerts$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.concertService.getByConcertName(term))
    );
  }

  toggleSearch() {
    this.concerts$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.concertService.getByConcertName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(concert: Concert) {
    this.concertEvent.emit(concert);
  }


}
