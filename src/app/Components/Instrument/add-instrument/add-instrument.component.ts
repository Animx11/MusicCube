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
  private instrumentType: InstrumentType;

  constructor(private instrumentService: InstrumentService) {}

  ngOnInit() {
    this.instrument = new Instrument();
    this.instrumentName = this.instrumentType = null;
  }

  addInstrument() {
    if (this.instrumentName === "")
      window.alert("Incomplete input");
    else {
      this.instrument.setInstrumentName(this.instrumentName);
      this.instrument.setInstrumentType(this.instrumentType);
      this.instrumentService.create(this.instrument).subscribe(
        res => {
          console.log("add-instrument-component received:");
          console.log(res);
          window.alert("Instrument added");
        },
        err => {
          console.error(err);
          window.alert("Error occured");
        }
      );
    }
  }
}
