import { Component, OnInit } from "@angular/core";
import { InstrumentService } from "src/app/Services/instrument.service";
import { Instrument } from "src/app/Class/Instrument";

@Component({
  selector: "app-add-instrument",
  templateUrl: "./add-instrument.component.html",
  styleUrls: ["./add-instrument.component.css"]
})
export class AddInstrumentComponent implements OnInit {
  private instrument: Instrument;
  private instrumentName: string;
  private type: string;

  constructor(private instrumentService: InstrumentService) {}

  ngOnInit() {
    this.instrument = new Instrument();
    this.instrumentName = this.type = "";
  }

  addInstrument() {
    if (this.instrumentName === "" || this.type === "")
      window.alert("Incomplete input");
    else {
      this.instrument.setInstrumentName(this.instrumentName);
      this.instrument.setType(this.type);
      this.instrumentService.create(this.instrument).subscribe(
        res => {
          console.log("add-instrument-component recieved:");
          console.log(res);
          window.alert("Instrument added");
        },
        err => {
          console.log(err);
          window.alert("Error occured");
        }
      );
    }
  }
}
