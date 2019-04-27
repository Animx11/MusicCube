import { Component, OnInit } from '@angular/core';
import { ArtistInBandService } from 'src/app/Services/artist-in-band.service';
import { ArtistService } from 'src/app/Services/artist.service';

@Component({
  selector: 'app-add-artist',
  templateUrl: './add-artist.component.html',
  styleUrls: ['./add-artist.component.css']
})
export class AddArtistComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit() {
  }

}
