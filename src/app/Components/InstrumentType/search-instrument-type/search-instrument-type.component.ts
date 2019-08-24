import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { InstrumentTypeService } from 'src/app/Services/instrument-type.service';
import { InstrumentType } from 'src/app/Class/InstrumentType';

@Component({
  selector: 'app-search-instrument-type',
  templateUrl: './search-instrument-type.component.html',
  styleUrls: ['./search-instrument-type.component.css']
})
export class SearchInstrumentTypeComponent implements OnInit {


  private searchOn: boolean;
  private listOn: boolean;
  private reachedLastPage: boolean;
  private pageCount: number;

  instrumentTypes$: Observable<InstrumentType[]>;
  instrumentTypePage: InstrumentType[];
  private searchTerms = new Subject<string>();

  @Output() instrumentTypeEvent = new EventEmitter<InstrumentType>();
  @Output() searchEvent = new EventEmitter();

  constructor(private instrumentTypeService: InstrumentTypeService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.instrumentTypes$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.instrumentTypeService.getByInstrumentTypeName(term))
    );
  }

  toggleSearch() {
    this.instrumentTypes$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.instrumentTypeService.getByInstrumentTypeName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(instrumentType: InstrumentType) {
    this.instrumentTypeEvent.emit(instrumentType);
  }

}
