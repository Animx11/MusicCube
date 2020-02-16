import { Component, OnInit } from "@angular/core";
import { InstrumentService } from "src/app/Services/instrument.service";
import { Instrument } from "src/app/Class/Instrument";
import { InstrumentType } from "src/app/Class/InstrumentType";

@Component({
  selector: "app-add-instrument",
  templateUrl: "./add-instrument.component.html",
  styleUrls: ["./add-instrument.component.css"]
})
export class AddInstrumentComponent implements OnInit {
  private instrument: Instrument;
  private instrumentName: string;
  private instrumentTypeName: InstrumentType;
  private about: string;

  private isInstrumentTypeClicked: boolean;
  private isInstrumentTypeSelected: boolean;

  constructor(private instrumentService: InstrumentService) {}

  ngOnInit() {
    this.instrument = new Instrument();
    this.instrumentName = this.instrumentTypeName = null;
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = false;
    this.about = '';
  }

  searchInstrumentType(){
    this.isInstrumentTypeClicked = true;
  }

  instrumentTypeEventHandler($event: any) {
    this.instrument.setInstrumentType($event);
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = true;
  }

  addInstrument() {
    if (this.instrumentName === "")
      window.alert("Incomplete input");
    else {
      this.instrument.setInstrumentName(this.instrumentName);
      this.instrument.setAboutInstrument(this.about);
      this.instrumentService.create(this.instrument).subscribe(
        res => {
          console.log("add-instrument-component received:");
          console.log(res);
          window.alert("Instrument added");
          this.ngOnInit();
        },
        err => {
          console.error(err);
          window.alert("Error occured");
        }
      );
    }
  }
}
