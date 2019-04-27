import { Component, OnInit } from '@angular/core';
import { ArtistService } from 'src/app/Services/artist.service';

@Component({
  selector: 'app-edit-artist',
  templateUrl: './edit-artist.component.html',
  styleUrls: ['./edit-artist.component.css']
})
export class EditArtistComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit() {
  }

}
