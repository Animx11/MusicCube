import { Component, OnInit } from '@angular/core';
import { ArtistService } from 'src/app/Services/artist.service';

@Component({
  selector: 'app-delete-artist',
  templateUrl: './delete-artist.component.html',
  styleUrls: ['./delete-artist.component.css']
})
export class DeleteArtistComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit() {
  }

}