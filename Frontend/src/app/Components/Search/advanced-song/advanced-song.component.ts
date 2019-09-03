import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Song} from '../../../Class/Song';

@Component({
  selector: 'app-advanced-song',
  templateUrl: './advanced-song.component.html',
  styleUrls: ['./advanced-song.component.css']
})
export class AdvancedSongComponent implements OnInit {

  constructor() { }
  @Output() songSearchEvent = new EventEmitter<Song[]>();
  ngOnInit() {
  }

}
