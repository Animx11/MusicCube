import { Component, OnInit } from '@angular/core';
import { ConcertService } from 'src/app/Services/concert.service';
import { Concert } from 'src/app/Class/Concert';

@Component({
  selector: 'app-edit-concert',
  templateUrl: './edit-concert.component.html',
  styleUrls: ['./edit-concert.component.css']
})
export class EditConcertComponent implements OnInit {

  selectedConcert: Concert;

  concert: Concert;
  concertName: string;
  startTime: Date;
  hour: number;
  minutes: number;

  comparedDate: Date;

  dateString: string[];

  isCityClicked: boolean;
  isCitySelected: boolean;
  isEditSelected: boolean;


  constructor(private concertService: ConcertService) { }

  ngOnInit() {
    this.concert = new Concert();
    this.startTime = null;
    this.minutes = this.hour = null;
  }

  concertEventHandler($event) {
    this.selectedConcert = $event;
    this.concertName = this.selectedConcert.concertName;
    this.isEditSelected = true;
    this.isCityClicked = false;
    this.isCitySelected = false;
    this.concert.setStartTime(this.selectedConcert.startTime);
    this.concert.setConcertCity(this.selectedConcert.concertCity);
  }
  searchEventHandler($event) {
    this.selectedConcert = null;
  }


  searchCity() {
    this.isCityClicked = true;
  }

  makeDate() {
    if (this.startTime !== null && this.hour !== null && this.minutes !== null) {
      this.dateString = this.startTime.toString().split('-');
      this.startTime = new Date(
        parseInt(this.dateString[0]), parseInt(this.dateString[1]) - 1, parseInt(this.dateString[2]),
        this.hour, this.minutes
      );
    } else {
      this.startTime = this.selectedConcert.startTime;
    }
  }

  cityEventHandler($event: any) {
    this.concert.setConcertCity($event);
    this.isCityClicked = false;
    this.isCitySelected = true;
  }


  delete() {
    this.concertService.delete(this.selectedConcert.id).subscribe(
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
    this.makeDate();
    if (this.concertName === this.selectedConcert.concertName && this.concert.getConcertCity() === this.selectedConcert.concertCity && this.startTime === this.selectedConcert.startTime) {
      window.alert('You need to do some changes before update');
    } else {
      this.concert.id = this.selectedConcert.id;
      this.concert.setConcertName(this.concertName);
      this.concert.setStartTime(this.startTime);
      this.concertService.edit(this.concert).subscribe(
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
    this.startTime = null;
    this.isEditSelected = false;
    this.selectedConcert = null;
    this.concert = new Concert();
    this.concertName = '';
    this.hour = this.minutes = null;
    this.startTime = null;
  }

}
