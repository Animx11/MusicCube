import { Component, OnInit, Input, SystemJsNgModuleLoader } from '@angular/core';

import { Concert } from 'src/app/Class/Concert';
import { ConcertService } from 'src/app/Services/concert.service';
import { stringify } from 'querystring';

@Component({
  selector: 'app-add-concert',
  templateUrl: './add-concert.component.html',
  styleUrls: ['./add-concert.component.css']
})
export class AddConcertComponent implements OnInit {
  concert: Concert;
  concertName: string;
  startTime: Date;
  hour: number;
  minutes: number;

  dateString: string[];

  isCityClicked: boolean;
  isCitySelected: boolean;

  constructor(
    private concertService: ConcertService,
  ) {}

  ngOnInit() {
    this.concert = new Concert();
    this.concertName = '';
    this.startTime = null;
    this.isCityClicked = false;
    this.isCitySelected = false;
    this.hour = 0;
    this.minutes = 0;
  }

  searchCity() {
    this.isCityClicked = true;
  }

  cityEventHandler($event: any) {
    this.concert.setConcertCity($event);
    this.isCityClicked = false;
    this.isCitySelected = true;
  }

  makeDate() {
    this.dateString = this.startTime.toString().split('-');
    this.startTime = new Date(
      parseInt(this.dateString[0]), parseInt(this.dateString[1]), parseInt(this.dateString[2]),
      this.hour, this.minutes
    );
  }

  addConcert() {

    if (
      this.concertName === '' ||
      this.startTime === null ||
      this.concert.getConcertCity === null
    ) {
      window.alert('Incomplete input');
    } else if (this.hour < 0 || this.minutes < 0 || this.hour > 23 || this.minutes > 59) {
      window.alert('Incorrect time');

    } else {
      this.concert.setConcertName(this.concertName);
      this.makeDate();
      this.concert.setStartTime(this.startTime);
      this.concertService.create(this.concert).subscribe(
        res => {
          console.log('add-concert-component received:');
          console.log(res);
          window.alert('Concert added');
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
