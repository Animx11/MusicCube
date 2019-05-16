import { Component, OnInit } from "@angular/core";
import { LocalizationService } from "src/app/Services/localization.service";
import { Localization } from "src/app/Class/Localization";
import { Location } from "@angular/common";

@Component({
  selector: "app-add-localization",
  templateUrl: "./add-localization.component.html",
  styleUrls: ["./add-localization.component.css"]
})
export class AddLocalizationComponent implements OnInit {
  private country: string;
  private city: string;
  private address: string;
  private localization: Localization;

  constructor(
    private localizationService: LocalizationService,
    private location: Location
  ) {}

  ngOnInit() {
    this.country = this.city = this.address = "";
    this.localization = new Localization();
  }

  save() {
    if (this.country === "") {
      window.alert("No input");
      return;
    }
    this.localization.setCity(this.city);
    this.localization.setCountry(this.country);
    this.localization.setAddress(this.address);
    this.localizationService
      .create(this.localization)
      .subscribe(res => console.log(res));
  }
}
