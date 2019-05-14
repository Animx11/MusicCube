import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { Band } from "src/app/Class/Band";
import { Localization } from "src/app/Class/Localization";

import { BandService } from "src/app/Services/band.service";
import { LocalizationService } from "src/app/Services/localization.service";

@Component({
  selector: "app-add-band",
  templateUrl: "./add-band.component.html",
  styleUrls: ["./add-band.component.css"]
})
export class AddBandComponent implements OnInit {
  private band: Band;
  private bandName: string;
  private creationYear: number;
  private formedIn: Localization;

  private formedInSelected: boolean;
  private localizationList: Localization[];

  constructor(
    private bandService: BandService,
    private localizationService: LocalizationService
  ) {}

  ngOnInit() {
    this.band = new Band();
    this.bandName = "";
    this.creationYear = 0;
    this.formedIn = null;

    this.formedInSelected = false;
  }

  selectFormedIn() {
    this.localizationService.list().subscribe(res => {
      console.log("add-band-component recieved:");
      console.log(res);
      this.localizationList = res.map(el => new Localization(el));
    });
    this.formedInSelected = true;
  }
  addFormedIn(origin: Localization) {
    this.band.setFormedIn(origin);
    this.formedInSelected = false;
  }
  addBand() {
    if (
      this.bandName === "" ||
      this.creationYear === 0 ||
      this.band.getFormedIn() == null
    )
      window.alert("Incomplete input");
    else {
      this.band.setBandName(this.bandName);
      this.band.setCreationDate(new Date(`${this.creationYear}-01-01`));

      this.bandService.create(this.band).subscribe(
        res => {
          console.log("add-band-component recieved:");
          console.log(res);
          window.alert("Band added");
        },
        err => {
          window.alert("Error occured");
          console.log(err);
        }
      );
    }
  }
}
