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

  constructor(private instrumentService: InstrumentService) {}

  ngOnInit() {
    this.instrument = new Instrument();
  }

  save() {}
}
