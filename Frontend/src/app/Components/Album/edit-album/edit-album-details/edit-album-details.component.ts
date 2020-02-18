import { Component, OnInit, Input } from '@angular/core';

import { Album } from 'src/app/Class/Album';

@Component({
  selector: 'app-edit-album-details',
  templateUrl: './edit-album-details.component.html',
  styleUrls: ['./edit-album-details.component.css'],
})
export class EditAlbumDetailsComponent implements OnInit {
  @Input() album: Album;

  constructor() {}

  ngOnInit() {}
}
