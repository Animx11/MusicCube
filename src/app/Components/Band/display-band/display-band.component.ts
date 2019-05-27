import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { BandService } from '../../../Services/band.service';
import {Band} from '../../../Class/Band';
import {Album} from '../../../Class/Album';
import {ArtistInBand} from '../../../Class/ArtistInBand';
import {ArtistInBandService} from '../../../Services/artist-in-band.service';

@Component({
  selector: 'app-display-band',
  templateUrl: './display-band.component.html',
  styleUrls: ['./display-band.component.css']
})
export class DisplayBandComponent implements OnInit {

  band: Band;
  albums: Album[];
  currentLnp: ArtistInBand[];
  pastLnp: ArtistInBand[];


  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private bandService: BandService,
    private artistInBandService: ArtistInBandService) { }

  ngOnInit() {
    this.getBand();
  }

  private getBand() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.bandService.getById(id).subscribe(
      res => {
        console.log('display-band-component received: ', res);
        this.band = new Band(res);
        this.getAlbums();
        this.getMembers();
      },
      err => console.error(err));
  }
  private getAlbums() {
    this.bandService.getBandAlbums(this.band.getId()).subscribe(res => {
      console.log('display-band-component received: ', res);
      this.albums = res.map(el => new Album(el));
    },
      err => console.error(err));
  }
  private getMembers() {
    this.artistInBandService.getByBandIdIsActive(this.band.getId(), true).subscribe(res => {
      console.log('display-band-component received: ', res);
      this.currentLnp = res.map(el => new ArtistInBand(el));
    }, err => console.error(err));
    this.artistInBandService.getByBandIdIsActive(this.band.getId(), false).subscribe(res => {
      console.log('display-band-component received: ', res);
      this.pastLnp = res.map(el => new ArtistInBand(el));
    }, err => console.error(err));
  }
}
