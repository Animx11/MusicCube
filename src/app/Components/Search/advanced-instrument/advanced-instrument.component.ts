import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Instrument} from '../../../Class/Instrument';

@Component({
  selector: 'app-advanced-instrument',
  templateUrl: './advanced-instrument.component.html',
  styleUrls: ['./advanced-instrument.component.css']
})
export class AdvancedInstrumentComponent implements OnInit {

  constructor() { }
  @Output() instrumentSearchEvent = new EventEmitter<Instrument[]>();
  ngOnInit() {
  }

}
