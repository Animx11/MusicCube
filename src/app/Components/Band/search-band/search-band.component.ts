import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { BandService } from "src/app/Services/band.service";
import { Band } from "src/app/Class/Band";

@Component({
  selector: "app-search-band",
  templateUrl: "./search-band.component.html",
  styleUrls: ["./search-band.component.css"]
})
export class SearchBandComponent implements OnInit {
  private searchOn: boolean;

  bands$: Observable<Band[]>;
  private searchTerms = new Subject<string>();

  @Output() bandEvent = new EventEmitter<Band>();

  constructor(private bandService: BandService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.bands$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.bandService.getByBandName(term))
    );
  }

  toggleSearch() {
    this.searchOn = !this.searchOn;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(band: Band) {
    this.toggleSearch();
    this.bandEvent.emit(band);
  }
}
