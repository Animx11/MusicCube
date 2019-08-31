import {Component, Input, OnInit} from '@angular/core';
import {Artist} from '../../../Class/Artist';

@Component({
  selector: 'app-artist-list-display',
  templateUrl: './artist-list-display.component.html',
  styleUrls: ['./artist-list-display.component.css']
})
export class ArtistListDisplayComponent implements OnInit {

  @Input() list: Artist[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
