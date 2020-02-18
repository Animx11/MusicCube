import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Genre} from '../../../Class/Genre';

@Component({
  selector: 'app-advanced-genre',
  templateUrl: './advanced-genre.component.html',
  styleUrls: ['./advanced-genre.component.css']
})
export class AdvancedGenreComponent implements OnInit {

  constructor() { }
  @Output() genreSearchEvent = new EventEmitter<Genre[]>();
  ngOnInit() {
  }

}
