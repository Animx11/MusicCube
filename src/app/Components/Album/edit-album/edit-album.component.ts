import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/Services/album.service';
import { Album } from 'src/app/Class/Album';

@Component({
  selector: 'app-edit-album',
  templateUrl: './edit-album.component.html',
  styleUrls: ['./edit-album.component.css']
})
export class EditAlbumComponent implements OnInit {
  selectedAlbum: Album;

  constructor(private albumService: AlbumService) {}

  ngOnInit(): void {}

  albumEventHandler($event) {
    this.selectedAlbum = $event;
  }
}
