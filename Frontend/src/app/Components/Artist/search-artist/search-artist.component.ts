import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Artist } from 'src/app/Class/Artist';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ArtistService } from 'src/app/Services/artist.service';
import { PersonService } from 'src/app/Services/person.service';
import { Person } from 'src/app/Class/Person';

@Component({
  selector: 'app-search-artist',
  templateUrl: './search-artist.component.html',
  styleUrls: ['./search-artist.component.css']
})
export class SearchArtistComponent implements OnInit {


  private searchOn: boolean;
  private listOn: boolean;
  private reachedLastPage: boolean;
  private pageCount: number;


  persons$: Observable<Artist[]>;
  personPage: Artist[];
  private searchTerms = new Subject<string>();

  @Output() artistEvent = new EventEmitter<Artist>();
  @Output() searchEvent = new EventEmitter();

  constructor(
    private artistService: ArtistService,
    private personService: PersonService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.persons$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.personService.getByAnything(term))
    );

  }

  toggleSearch() {
    this.persons$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.personService.getByAnything(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(artist: Artist) {
    this.artistEvent.emit(artist);
  }

}
