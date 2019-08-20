import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { BandService } from '../../../Services/band.service';
import {Band} from '../../../Class/Band';
import {Album} from '../../../Class/Album';
import {ArtistInBand} from '../../../Class/ArtistInBand';
import {ArtistInBandService} from '../../../Services/artist-in-band.service';
import {ArtistActivityDisplay} from '../../../Class/ArtistActivityDisplay';
import {isNull} from 'util';

@Component({
  selector: 'app-display-band',
  templateUrl: './display-band.component.html',
  styleUrls: ['./display-band.component.css']
})
export class DisplayBandComponent implements OnInit {

  band: Band;
  albums: Album[];
  lnp: ArtistInBand[];
  artistDisplays: ArtistActivityDisplay[];


  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private bandService: BandService,
    private artistInBandService: ArtistInBandService) {
    this.artistDisplays = [];
  }

  ngOnInit() {
    this.getBand();
  }

  private getBand() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.bandService.getById(id).subscribe(
      res => {
        console.log('display-band-component received band: ', res);
        this.band = new Band(res);
        this.getAlbums();
        this.getMembers();
      },
      err => console.error(err));
  }
  private getAlbums() {
    this.bandService.getBandAlbums(this.band.getId()).subscribe(res => {
      console.log('display-band-component received albums: ', res);
      this.albums = res.map(el => new Album(el));
    },
      err => console.error(err));
  }
  private getMembers() {
    this.artistInBandService.getByBandId(this.band.id).subscribe(
      res => {
        console.log('display-band-component received artists activities: ', res);
        this.lnp = res.map(el => new ArtistInBand(el));
        this.handleActivity();
      },
      err => console.error(err)
    );
  }
  /*
    Puts all activity periods of one artist together, allowing us to display them next to his name
   */
  private handleActivity() {
    const presentIds = [];
    let periodString = '';
    this.lnp.forEach(el => {
      const begin = isNull(el.activityStart) ? null : new Date(el.activityStart);
      const end = isNull(el.activityEnd) ? null : new Date(el.activityEnd);
      /*
        Checking all possibilities, in order to properly describe, when artist was active
       */
      if (isNull(begin)) {
        if (isNull(end)) { // all null
          periodString = ' unknown';
        } else {
          periodString = (' ? - ' + end.getFullYear().toString()); // only end is known
        }
      } else if (isNull(end)) { // only start...
        if (el.isActive) { // ...and artist is still active
          periodString = (' since ' + begin.getFullYear().toString());
        } else { // ...and artist is inactive, but end time is unknown
          periodString = (' ' + begin.getFullYear().toString() + ' - ?');
        }
      } else if (begin.getFullYear() === end.getFullYear()) { // no nulls, active less than one year
        periodString = (' ' + begin.getFullYear().toString());
      } else { // no nulls, active multiple years
        // console.log('i poszedł w maliny')
        // console.log(end)
        periodString = (
          ' ' +
          begin.getFullYear().toString() +
          ' - ' +
          end.getFullYear().toString()
        );
      }


      const index = presentIds.indexOf(el.artist.id);
      if (index >= 0) {
        this.artistDisplays[index].addToPeriods(periodString);
        if (el.isActive) {
          this.artistDisplays[index].isCurrent();
        }
      } else {
        const aad = new ArtistActivityDisplay(el.artist.id, el.artist.stageName);
        presentIds.push(el.artist.id);
        aad.addToPeriods(periodString);
        if (el.isActive) {
          aad.isCurrent();
        }
        this.artistDisplays.push(aad);
      }
    });
  }
}
