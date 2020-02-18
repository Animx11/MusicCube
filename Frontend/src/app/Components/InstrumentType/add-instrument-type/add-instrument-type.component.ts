import { Component, OnInit } from '@angular/core';
import { InstrumentType } from 'src/app/Class/InstrumentType';
import { InstrumentTypeService } from 'src/app/Services/instrument-type.service';

@Component({
  selector: 'app-add-instrument-type',
  templateUrl: './add-instrument-type.component.html',
  styleUrls: ['./add-instrument-type.component.css']
})
export class AddInstrumentTypeComponent implements OnInit {

  instrumentType: InstrumentType;
  instrumentTypeName: string;
  aboutType: string;

  constructor(private instrumentTypeService: InstrumentTypeService) { }

  ngOnInit() {
    this.instrumentType = new InstrumentType();
    this.instrumentTypeName = '';
    this.aboutType = '';
  }

  addInstrumentType() {
    if (this.instrumentTypeName === '') {
      window.alert('Incomplete input');
    } else {
      this.instrumentType.setInstrumentTypeName(this.instrumentTypeName);
      this.instrumentType.setAboutType(this.aboutType);
      this.instrumentTypeService.create(this.instrumentType).subscribe(
        res => {
          console.log('add-instrument-type-component received:');
          console.log(res);
          window.alert('Instrument type added');
          this.ngOnInit();
        },
        err => {
          console.error(err);
          window.alert('Error occurred');
        }
      );
    }
  }

}
