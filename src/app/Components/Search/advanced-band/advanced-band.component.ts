import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Band} from '../../../Class/Band';

@Component({
  selector: 'app-advanced-band',
  templateUrl: './advanced-band.component.html',
  styleUrls: ['./advanced-band.component.css']
})
export class AdvancedBandComponent implements OnInit {

  constructor() { }
  @Output() bandSearchEvent = new EventEmitter<Band[]>();
  ngOnInit() {
  }

}
