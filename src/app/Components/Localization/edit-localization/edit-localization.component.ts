import { Component, OnInit } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { LocalizationService } from "src/app/Services/localization.service";
import { Localization } from "src/app/Class/Localization";

@Component({
  selector: "app-edit-localization",
  templateUrl: "./edit-localization.component.html",
  styleUrls: ["./edit-localization.component.css"],
})
export class EditLocalizationComponent implements OnInit {
  localizations$: Observable<Localization[]>;
  private searchTerms = new Subject<string>();
  private selectedLocalization: Localization;

  constructor(private localizationService: LocalizationService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(localization: Localization): void {
    this.selectedLocalization = localization;
  }

  ngOnInit(): void {
    this.localizations$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.localizationService.getByAnything(term))
    );
  }
}
