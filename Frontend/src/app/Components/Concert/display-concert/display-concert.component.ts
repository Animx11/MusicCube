import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ConcertService } from '../../../Services/concert.service';
import {Concert} from '../../../Class/Concert';

@Component({
  selector: 'app-display-concert',
  templateUrl: './display-concert.component.html',
  styleUrls: ['./display-concert.component.css']
})
export class DisplayConcertComponent implements OnInit {

  concert: Concert;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private concertService: ConcertService) { }

  ngOnInit() {
    this.getConcert();
  }

  private getConcert() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.concertService.getById(id).subscribe(
      res => {
        this.concert = new Concert(res);
        console.log('display-concert-component received: ', res);
      },
      err => console.error(err));
  }
}