import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { InstrumentService } from '../../../Services/instrument.service';
import {Instrument} from '../../../Class/Instrument';

@Component({
  selector: 'app-display-instrument',
  templateUrl: './display-instrument.component.html',
  styleUrls: ['./display-instrument.component.css']
})
export class DisplayInstrumentComponent implements OnInit {

  instrument: Instrument;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private instrumentService: InstrumentService) { }

  ngOnInit() {
    this.getInstrument();
  }

  private getInstrument() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.instrumentService.getById(id).subscribe(
      res => {
        this.instrument = new Instrument(res);
        console.log('display-instrument-component received: ', res);
      },
      err => console.error(err));
  }
}
