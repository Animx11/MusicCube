import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { CountryService } from 'src/app/Services/country.service';
import { Country } from 'src/app/Class/Country';

@Component({
  selector: 'app-search-country',
  templateUrl: './search-country.component.html',
  styleUrls: ['./search-country.component.css']
})
export class SearchCountryComponent implements OnInit {

  searchOn: boolean;
  listOn: boolean;
  reachedLastPage: boolean;
  pageCount: number;

  countries$: Observable<Country[]>;
  countryPage: Country[];
  searchTerms = new Subject<string>();

  @Output() countryEvent = new EventEmitter<Country>();
  @Output() searchEvent = new EventEmitter();

  constructor(private countryService: CountryService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.countries$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.countryService.getByCountryName(term))
    );
  }

  toggleSearch() {
    this.countries$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.countryService.getByCountryName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(country: Country) {
    this.countryEvent.emit(country);
  }

}
