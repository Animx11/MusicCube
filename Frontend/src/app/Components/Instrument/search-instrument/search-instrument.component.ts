import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { InstrumentService } from 'src/app/Services/instrument.service';
import { Instrument } from 'src/app/Class/Instrument';

@Component({
  selector: 'app-search-instrument',
  templateUrl: './search-instrument.component.html',
  styleUrls: ['./search-instrument.component.css']
})
export class SearchInstrumentComponent implements OnInit {
  searchOn: boolean;

  instruments$: Observable<Instrument[]>;
  searchTerms = new Subject<string>();

  @Output() instrumentEvent = new EventEmitter<Instrument>();
  @Output() searchEvent = new EventEmitter();

  constructor(private instrumentService: InstrumentService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.instruments$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) =>
        this.instrumentService.getByInstrumentName(term)
      )
    );
  }

  toggleSearch() {
    this.instruments$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) =>
        this.instrumentService.getByInstrumentName(term)
      )
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
      this.searchTerms.next(term);
    }
  }

  onSelect(instrument: Instrument) {
    this.instrumentEvent.emit(instrument);
  }

}
