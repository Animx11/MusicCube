import {Component, Input, OnInit} from '@angular/core';
import {Band} from '../../../Class/Band';

@Component({
  selector: 'app-band-list-display',
  templateUrl: './band-list-display.component.html',
  styleUrls: ['./band-list-display.component.css', '../../../app.component.css']
})
export class BandListDisplayComponent implements OnInit {

  @Input() list: Band[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
