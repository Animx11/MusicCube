import { Component, OnInit } from '@angular/core';
import { InstrumentService } from 'src/app/Services/instrument.service';
import { Instrument } from 'src/app/Class/Instrument';
import { InstrumentType } from 'src/app/Class/InstrumentType';

@Component({
  selector: 'app-edit-instrument',
  templateUrl: './edit-instrument.component.html',
  styleUrls: ['./edit-instrument.component.css']
})
export class EditInstrumentComponent implements OnInit {

  private selectedInstrument: Instrument;

  private instrument: Instrument;
  private instrumentName: string;
  private instrumentTypeName: InstrumentType;

  private isInstrumentTypeClicked: boolean;
  private isInstrumentTypeSelected: boolean;
  private isEditSelected: boolean;

  constructor(private instrumentService: InstrumentService) {}

  ngOnInit() {
    this.instrument = new Instrument();
    this.instrumentName = '';
    this.instrumentTypeName = null;
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = false;
  }

  searchInstrumentType() {
    this.isInstrumentTypeClicked = true;
  }

  instrumentTypeEventHandler($event: any) {
    this.instrument.setInstrumentType($event);
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = true;
  }

  instrumentEventHandler($event) {
    this.selectedInstrument = $event;
    this.instrumentName = this.selectedInstrument.instrumentName;
    this.isEditSelected = true;
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = false;
    this.instrument.setInstrumentType(this.selectedInstrument.instrumentType);
    if(this.instrument.getInstrumentType !== null) {
      this.isInstrumentTypeSelected = true;
    }
  }
  searchEventHandler($event) {
    this.selectedInstrument = null;
  }

  delete() {
    this.instrumentService.delete(this.selectedInstrument.id).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occured');
      }
    );
  }

  update() {
    if(this.instrumentName === this.selectedInstrument.instrumentName && this.instrument.getInstrumentType() === this.selectedInstrument.instrumentType) {
      window.alert('You need to do some changes before update');
    } else {
      this.instrument.id = this.selectedInstrument.id;
      this.instrument.setInstrumentName(this.instrumentName);
      this.instrumentService.edit(this.instrument).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occured');
        }
      );
    }
  }

  reset() {
    this.isEditSelected = false;
    this.selectedInstrument = null;
    this.instrument = new Instrument();
    this.instrumentName = '';
    this.instrumentTypeName = null;
  }

}
