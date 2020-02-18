import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { CityService } from 'src/app/Services/city.service';
import { City } from 'src/app/Class/City';

@Component({
  selector: 'app-search-city',
  templateUrl: './search-city.component.html',
  styleUrls: ['./search-city.component.css']
})
export class SearchCityComponent implements OnInit {


  searchOn: boolean;
  listOn: boolean;
  reachedLastPage: boolean;
  pageCount: number;

  cities$: Observable<City[]>;
  cityPage: City[];
  searchTerms = new Subject<string>();

  @Output() cityEvent = new EventEmitter<City>();
  @Output() searchEvent = new EventEmitter();

  constructor(private cityService: CityService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.cities$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.cityService.getByCityName(term))
    );
  }

  toggleSearch() {
    this.cities$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.cityService.getByCityName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(city: City) {
    this.cityEvent.emit(city);
  }


}
