import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { Band } from "src/app/Class/Band";

import { BandService } from "src/app/Services/band.service";

@Component({
  selector: "app-add-band",
  templateUrl: "./add-band.component.html",
  styleUrls: ["./add-band.component.css"]
})
export class AddBandComponent implements OnInit {
  private band: Band;
  private bandName: string;
  private creationYear: number;

  private formedInSelected: boolean;

  constructor(private bandService: BandService) {}

  ngOnInit() {
    this.band = new Band();
    this.bandName = "";
    this.creationYear = 0;
  }

  localizationEventHandler($event: any) {
    this.band.setFormedIn($event);
    console.log(this.band);
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
