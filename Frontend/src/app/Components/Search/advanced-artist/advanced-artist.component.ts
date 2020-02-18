import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Band} from '../../../Class/Band';
import {City} from '../../../Class/City';
import {Artist} from '../../../Class/Artist';
import {ArtistService} from '../../../Services/artist.service';
import {Instrument} from '../../../Class/Instrument';
import {Country} from '../../../Class/Country';

@Component({
  selector: 'app-advanced-artist',
  templateUrl: './advanced-artist.component.html',
  styleUrls: ['./advanced-artist.component.css', '../../../app.component.css']
})
export class AdvancedArtistComponent implements OnInit {

  constructor(
    private service: ArtistService
  ) {
    this.isFromCity = true;
    this.isFromCountry = false;
    this.city = null;
    this.country = null;
    this.band = null;
    this.instrument = null;
  }

  isFromCity: boolean;
  isFromCountry: boolean;
  band: Band;
  country: Country;
  city: City;
  instrument: Instrument;
  result: Artist[];

  @Output() artistSearchEvent = new EventEmitter<Artist[]>();
  @Output() noResultEvent = new EventEmitter<string>();

  ngOnInit() {
  }


  bandEvent($event) {
    this.band = new Band($event);
  }
  countryEvent($event) {
    this.country = new Country($event);
  }
  cityEvent($event) {
    this.city = new City($event);
  }
  instrumentEvent($event) {
    this.instrument = new Instrument($event);
  }
  toggleIsFrom(where: string) {
    switch (where) {
      case 'city':
        this.isFromCity = true;
        this.isFromCountry = false;
        this.country = null;
        break;
      case 'country':
        this.isFromCountry = true;
        this.isFromCity = false;
        this.city = null;
    }
  }

  search() {
    if (this.band || this.city || this.country || this.instrument) {
      this.service.advancedSearch(this.band, this.city, this.country, this.instrument).subscribe(
        res => {
          console.log(res);
          this.result = res.map(el => new Artist(el));
          if (this.result.length) {
            this.artistSearchEvent.emit(this.result);
          } else {
            this.noResultEvent.emit('artists');
          }
        },
        err => {
          console.error(err);
        }
      );
    } else {
      window.alert('Provide at least one parameter');
    }
  }

  resetBand() {
    this.band = null;
  }
  resetInstrument() {
    this.instrument = null;
  }
  resetCity() {
    this.city = null;
  }
  resetCountry() {
    this.country = null;
  }
}
