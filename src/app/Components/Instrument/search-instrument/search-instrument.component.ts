import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { InstrumentService } from "src/app/Services/instrument.service";
import { Instrument } from "src/app/Class/Instrument";

@Component({
  selector: "app-search-instrument",
  templateUrl: "./search-instrument.component.html",
  styleUrls: ["./search-instrument.component.css"]
})
export class SearchInstrumentComponent implements OnInit {
  private searchOn: boolean;

  instruments$: Observable<Instrument[]>;
  private searchTerms = new Subject<string>();

  @Output() instrumentEvent = new EventEmitter<Instrument>();

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
    this.searchOn = !this.searchOn;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(instrument: Instrument) {
    this.toggleSearch();
    this.instrumentEvent.emit(instrument);
  }
}
