import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ArtistService } from '../../../Services/artist.service';
import {Artist} from '../../../Class/Artist';
import {ArtistInBand} from '../../../Class/ArtistInBand';
import {ArtistInBandService} from '../../../Services/artist-in-band.service';

@Component({
  selector: 'app-display-artist',
  templateUrl: './display-artist.component.html',
  styleUrls: ['./display-artist.component.css']
})
export class DisplayArtistComponent implements OnInit {

  artist: Artist;
  bands: ArtistInBand[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private artistService: ArtistService,
    private artistInBandService: ArtistInBandService) { }

  ngOnInit() {
    this.getArtist();
  }

  private getArtist() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.artistService.getById(id).subscribe(
      res => {
        console.log('display-artist-component received: ', res);
        this.artist = new Artist(res);
        this.getBands();
      },
      err => console.error(err));
  }
  private getBands() {
    this.artistInBandService.getByArtistId(this.artist.id).subscribe(res =>{
      console.log('display-artist-component received: ', res);
      this.bands = res.map(el => new ArtistInBand(el));
    },
    err => console.error(err));
  }
}
