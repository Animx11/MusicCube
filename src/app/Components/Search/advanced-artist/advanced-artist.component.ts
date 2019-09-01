import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Band} from '../../../Class/Band';
import {City} from '../../../Class/City';
import {Artist} from '../../../Class/Artist';
import {ArtistService} from '../../../Services/artist.service';

@Component({
  selector: 'app-advanced-artist',
  templateUrl: './advanced-artist.component.html',
  styleUrls: ['./advanced-artist.component.css', '../../../app.component.css']
})
export class AdvancedArtistComponent implements OnInit {

  private band: Band;
  private origin: City;
  private result: Artist[];

  constructor(
    private service: ArtistService
  ) { }

  ngOnInit() {
  }

  @Output() artistSearchEvent = new EventEmitter<Artist[]>();


  bandEvent($event) {
    this.band = new Band($event);
  }
  cityEvent($event) {
    this.origin = new City($event);
  }

  search() {
    if (! (this.band || this.origin) ) {
      window.alert('Provide at least one parameter');
    } else if (! this.band) {
      this.service.advancedSearch(null, this.origin.getId()).subscribe(
        res => {
          this.result = res.map(el => new Artist(el));
          console.log('Advanced artist search received artists:', res);
          this.artistSearchEvent.emit(this.result);
        },
        err => {
          console.error(err);
        }
      );
    } else if (! this.origin) {
      this.service.advancedSearch(this.band.getId()).subscribe(
        res => {
          this.result = res.map(el => new Artist(el));
          console.log('Advanced artist search received artists:', res);
          this.artistSearchEvent.emit(this.result);
        },
        err => {
          console.error(err);
        }
      );
    } else {
      this.service.advancedSearch(this.band.getId(), this.origin.getId()).subscribe(
        res => {
          this.result = res.map(el => new Artist(el));
          console.log('Advanced artist search received artists:', res);
          this.artistSearchEvent.emit(this.result);
        },
        err => {
          console.error(err);
        }
      );
    }
  }

  resetOrigin() {
    this.origin = null;
  }
  resetBand() {
    this.band = null;
  }
}
