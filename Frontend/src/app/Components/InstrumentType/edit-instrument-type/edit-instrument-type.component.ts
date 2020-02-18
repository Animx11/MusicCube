import { Component, OnInit } from '@angular/core';
import { InstrumentType } from 'src/app/Class/InstrumentType';
import { InstrumentTypeService } from 'src/app/Services/instrument-type.service';

@Component({
  selector: 'app-edit-instrument-type',
  templateUrl: './edit-instrument-type.component.html',
  styleUrls: ['./edit-instrument-type.component.css']
})
export class EditInstrumentTypeComponent implements OnInit {

  selectedInstrumentType: InstrumentType;

  instrumentType: InstrumentType;
  instrumentTypeName: string;
  aboutType: string;

  isEditSelected: boolean;

  isInstrumentTypeClicked: boolean;
  isInstrumentTypeSelected: boolean;


  constructor(private instrumentTypeService: InstrumentTypeService) { }

  ngOnInit() {
    this.instrumentType = new InstrumentType();
    this.instrumentTypeName = '';
    this.aboutType = '';
  }

  instrumentTypeEventHandler($event) {
    this.selectedInstrumentType = $event;
    this.instrumentTypeName = this.selectedInstrumentType.instrumentTypeName;
    this.aboutType = this.selectedInstrumentType.aboutType;
    this.isEditSelected = true;
    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeSelected = false;
  }
  searchEventHandler($event) {
    this.selectedInstrumentType = null;
  }


  delete() {
    this.instrumentTypeService.delete(this.selectedInstrumentType.id).subscribe(
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
    if (this.instrumentTypeName === this.selectedInstrumentType.instrumentTypeName && this.aboutType === this.selectedInstrumentType.aboutType) {
      window.alert('You need to do some changes before update');
    } else {
      this.instrumentType.id = this.selectedInstrumentType.id;
      this.instrumentType.setInstrumentTypeName(this.instrumentTypeName);
      this.instrumentType.setAboutType(this.aboutType);
      this.instrumentTypeService.edit(this.instrumentType).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occurred');
        }
      );

    }
  }

  reset() {
    this.isEditSelected = false;
    this.selectedInstrumentType = null;
    this.instrumentType = new InstrumentType();
    this.instrumentTypeName = '';
    this.aboutType = '';
  }

}
