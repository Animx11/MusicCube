import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Band} from '../../../Class/Band';
import {Country} from '../../../Class/Country';
import {City} from '../../../Class/City';
import {Genre} from '../../../Class/Genre';
import {BandService} from '../../../Services/band.service';

@Component({
  selector: 'app-advanced-band',
  templateUrl: './advanced-band.component.html',
  styleUrls: ['./advanced-band.component.css']
})
export class AdvancedBandComponent implements OnInit {

  isFromCity: boolean;
  isFromCountry: boolean;
  country: Country;
  city: City;
  genre: Genre;
  result: Band[];

  constructor(private service: BandService) {
    this.isFromCity = true;
    this.isFromCountry = false;
    this.city = null;
    this.country = null;
    this.genre = null;
  }
  @Output() bandSearchEvent = new EventEmitter<Band[]>();
  @Output() noResultEvent = new EventEmitter<string>();
  ngOnInit() {
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
  countryEvent($event) {
    this.country = new Country($event);
  }
  cityEvent($event) {
    this.city = new City($event);
  }
  genreEvent($event) {
    this.genre = new Genre($event);
  }
  resetGenre() {
    this.genre = null;
  }
  resetCity() {
    this.city = null;
  }
  resetCountry() {
    this.country = null;
  }

  search() {
    if (this.city || this.country || this.genre) {
      this.service.advancedSearch(this.city, this.country, this.genre).subscribe(
        res => {
          this.result = res.map(el => new Band(el));
          console.log('Advanced band search received bands:', res);
          if (this.result.length) {
            this.bandSearchEvent.emit(this.result);
          } else {
            this.noResultEvent.emit('bands');
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


}
