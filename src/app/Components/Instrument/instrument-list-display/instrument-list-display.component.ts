import {Component, Input, OnInit} from '@angular/core';
import {Instrument} from '../../../Class/Instrument';

@Component({
  selector: 'app-instrument-list-display',
  templateUrl: './instrument-list-display.component.html',
  styleUrls: ['./instrument-list-display.component.css', '../../../app.component.css']
})
export class InstrumentListDisplayComponent implements OnInit {

  @Input() list: Instrument[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
