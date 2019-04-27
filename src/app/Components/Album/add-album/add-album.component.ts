import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/Services/album.service';

@Component({
  selector: 'app-add-album',
  templateUrl: './add-album.component.html',
  styleUrls: ['./add-album.component.css']
})
export class AddAlbumComponent implements OnInit {

  constructor(private albumService: AlbumService) { }

  ngOnInit() {
  }

}
