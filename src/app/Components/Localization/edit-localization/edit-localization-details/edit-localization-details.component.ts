import { Component, OnInit, Input } from "@angular/core";
import { Localization } from "src/app/Class/Localization";
import { LocalizationService } from "src/app/Services/localization.service";

@Component({
  selector: "app-edit-localization-details",
  templateUrl: "./edit-localization-details.component.html",
  styleUrls: ["./edit-localization-details.component.css"]
})
export class EditLocalizationDetailsComponent implements OnInit {
  @Input() private localization: Localization;

  constructor(private localizationService: LocalizationService) {}

  ngOnInit() {}

  save(): void {
    this.localizationService
      .edit(this.localization)
      .subscribe(res => console.log(res));
  }

  delete(): void {
    this.localizationService
      .delete(this.localization.getId())
      .subscribe(res => {
        console.log(res);
        this.localization = null;
      });
  }
}
