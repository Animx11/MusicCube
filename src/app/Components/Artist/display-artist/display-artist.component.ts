import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ArtistService } from '../../../Services/artist.service';
import {Artist} from '../../../Class/Artist';
import {ArtistActivity} from '../../../Class/ArtistActivity';
import {ArtistActivityService} from '../../../Services/artist-activity.service';
import {ArtistActivityDisplay} from '../../../Class/ArtistActivityDisplay';

@Component({
  selector: 'app-display-artist',
  templateUrl: './display-artist.component.html',
  styleUrls: ['./display-artist.component.css']
})
export class DisplayArtistComponent implements OnInit {

  artist: Artist;
  activities: ArtistActivity[];
  displays: ArtistActivityDisplay[] = [];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private artistService: ArtistService,
    private artistInBandService: ArtistActivityService) { }

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
      this.activities = res.map(el => new ArtistActivity(el));
      this.handleActivities();
    },
    err => console.error(err));
  }

  handleActivities() {
    const presentIds = [];
    this.activities.forEach(el => {
      const periodString = ArtistActivityDisplay.buildPeriodString(el);

      const index = presentIds.indexOf(el.band.id);
      if (index >= 0) {
        this.displays[index].addToPeriods(periodString);
        if (el.isActive) {
          this.displays[index].isCurrent();
        }
      } else {
        const aad = new ArtistActivityDisplay(
          this.artist.id,
          this.artist.stageName,
          el.band.id,
          el.band.bandName
        );
        presentIds.push(el.band.id);
        aad.addToPeriods(periodString);
        if (el.isActive) {
          aad.isCurrent();
        }
        this.displays.push(aad);
      }
    });
  }
}
