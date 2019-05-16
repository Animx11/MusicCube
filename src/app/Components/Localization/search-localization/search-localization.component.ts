import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { LocalizationService } from "src/app/Services/localization.service";
import { Localization } from "src/app/Class/Localization";

@Component({
  selector: "app-search-localization",
  templateUrl: "./search-localization.component.html",
  styleUrls: ["./search-localization.component.css"]
})
export class SearchLocalizationComponent implements OnInit {
  private searchOn: boolean;

  localizations$: Observable<Localization[]>;
  private searchTerms = new Subject<string>();

  @Output() localizationEvent = new EventEmitter<Localization>();

  constructor(private localizationService: LocalizationService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.localizations$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.localizationService.getByAnything(term))
    );
  }

  toggleSearch() {
    this.searchOn = !this.searchOn;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(localization: Localization) {
    this.toggleSearch();
    this.localizationEvent.emit(localization);
  }
}
