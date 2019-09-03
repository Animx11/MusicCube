import {Component, Input, OnInit} from '@angular/core';
import {Song} from '../../../Class/Song';

@Component({
  selector: 'app-song-list-display',
  templateUrl: './song-list-display.component.html',
  styleUrls: ['./song-list-display.component.css', '../../../app.component.css']
})
export class SongListDisplayComponent implements OnInit {

  @Input() list: Song[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
