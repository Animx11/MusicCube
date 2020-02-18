import { Component, OnInit } from '@angular/core';
import { BandService } from 'src/app/Services/band.service';
import { Band } from 'src/app/Class/Band';

@Component({
  selector: 'app-edit-band',
  templateUrl: './edit-band.component.html',
  styleUrls: ['./edit-band.component.css']
})
export class EditBandComponent implements OnInit {

  band: Band;
  selectedBand: Band;

  bandName: string;
  creationYear: Date;

  isCityClicked: boolean;
  isCitySelected: boolean;
  isEditSelected: boolean;

  constructor(private bandService: BandService) { }

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


  bandEventHandler($event) {
    this.selectedBand = $event;
    this.bandName = this.selectedBand.bandName;
    this.isEditSelected = true;
    this.isCityClicked = false;
    this.isCitySelected = false;
    if(this.selectedBand.formedIn){
      this.band.setFormedIn(this.selectedBand.formedIn);
    }
    this.band.setCreationDate(this.selectedBand.creationDate);
  }
  searchEventHandler($event) {
    this.selectedBand = null;
  }


  delete() {
    this.bandService.delete(this.selectedBand.id).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occurred');
      }
    );
  }

  update() {
    if (this.bandName === this.selectedBand.bandName && this.band.getFormedIn() === this.selectedBand.formedIn && this.creationYear === null) {
      window.alert('You need to do some changes before update');
    } else {
      this.band.id = this.selectedBand.id;
      this.band.setBandName(this.bandName);
      if (this.creationYear !== null) {
        this.band.setCreationDate(this.creationYear);
      }
      this.bandService.edit(this.band).subscribe(
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
    this.selectedBand = null;
    this.band = new Band();
    this.bandName = '';
    this.creationYear = null;
    this.isCityClicked = false;
    this.isCitySelected = false;
  }


}
