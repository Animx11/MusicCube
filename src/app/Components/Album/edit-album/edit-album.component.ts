import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/Services/album.service';

@Component({
  selector: 'app-edit-album',
  templateUrl: './edit-album.component.html',
  styleUrls: ['./edit-album.component.css']
})
export class EditAlbumComponent implements OnInit {

  constructor(private albumService: AlbumService) { }

  ngOnInit() {
  }

}