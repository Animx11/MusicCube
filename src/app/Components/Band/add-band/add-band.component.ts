import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Band } from 'src/app/Class/Band';

import { BandService } from 'src/app/Services/band.service';

@Component({
  selector: 'app-add-band',
  templateUrl: './add-band.component.html',
  styleUrls: ['./add-band.component.css']
})
export class AddBandComponent implements OnInit {
  band: Band;

  bandName: string;
  creationYear: Date;

  isCityClicked: boolean;


  isCitySelected: boolean;


  constructor(private bandService: BandService) {}

  ngOnInit() {
    this.band = new Band();
    this.bandName = '';
    this.creationYear = null;
    this.isCityClicked = false;
    this.isCitySelected = false;
  }

  searchCity(){
    this.isCityClicked = true;
  }

  cityEventHandler($event: any) {
    this.band.setFormedIn($event);
    this.isCityClicked = false;
    this.isCitySelected = true;
  }

  addBand() {
    if (
      this.bandName === '' ||
      this.creationYear === null ||
      this.band.getFormedIn() === null
    ) {
      window.alert('Incomplete input');
    }
    else {
      this.band.setBandName(this.bandName);
      this.band.setCreationDate(new Date(`${this.creationYear}-01-01`));

      this.bandService.create(this.band).subscribe(
        res => {
          console.log('add-band-component received:');
          console.log(res);
          window.alert('Band added');
          this.ngOnInit();
        },
        err => {
          window.alert('Error occurred');
          console.error(err);
        }
      );
    }
  }
}
