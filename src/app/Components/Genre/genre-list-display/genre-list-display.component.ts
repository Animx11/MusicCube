import {Component, Input, OnInit} from '@angular/core';
import {Genre} from '../../../Class/Genre';

@Component({
  selector: 'app-genre-list-display',
  templateUrl: './genre-list-display.component.html',
  styleUrls: ['./genre-list-display.component.css', '../../../app.component.css']
})
export class GenreListDisplayComponent implements OnInit {

  @Input() list: Genre[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
