import {Component, Input, OnInit} from '@angular/core';
import {Album} from '../../../Class/Album';

@Component({
  selector: 'app-album-list-display',
  templateUrl: './album-list-display.component.html',
  styleUrls: ['./album-list-display.component.css', '../../../app.component.css']
})
export class AlbumListDisplayComponent implements OnInit {

  @Input() list: Album[];

  constructor() {
    this.list = [];
  }

  ngOnInit() {
  }

}
