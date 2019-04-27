import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/Services/album.service';

@Component({
  selector: 'app-delete-album',
  templateUrl: './delete-album.component.html',
  styleUrls: ['./delete-album.component.css']
})
export class DeleteAlbumComponent implements OnInit {

  constructor(private albumService: AlbumService) { }

  ngOnInit() {
  }

}
