import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Band} from '../../../Class/Band';
import {BandService} from '../../../Services/band.service';

@Component({
  selector: 'app-advanced-band',
  templateUrl: './advanced-band.component.html',
  styleUrls: ['./advanced-band.component.css']
})
export class AdvancedBandComponent implements OnInit {

  result: Band[];

  constructor(private service: BandService) {
  }
  @Output() bandSearchEvent = new EventEmitter<Band[]>();
  @Output() noResultEvent = new EventEmitter<string>();
  @Output() clearEvent = new EventEmitter<void>();

  names: string;
  newName: string;
  nonames: string;
  newNoName: string;
  yearAfter: number;
  yearBefore: number;
  countries: string;
  newCountry: string;
  nocountries: string;
  newNoCountry: string;
  genres: string;
  newGenre: string;
  ngOnInit() {
    this.clear();
  }

  search() {
    let params: Map<string, string>;
    params = new Map<string, string>();
    if (this.names) {
      params.set('name', this.names.slice(0, this.names.length - 1));
    }
    if (this.nonames) {
      params.set('noname', this.nonames.slice(0, this.names.length - 1));
    }
    if (this.yearAfter) {
      // const date: Date = new Date(this.yearAfter, 0);
      params.set('formedafter', this.yearAfter.toString());
    }
    if (this.yearBefore) {
      // const date: Date = new Date(this.yearBefore, 0);
      params.set('formedbefore', this.yearBefore.toString());
    }
    if (this.countries) {
      params.set('country', this.countries);
    }
    if (this.nocountries) {
      params.set('nocountry', this.nocountries);
    }
    if (this.genres) {
      params.set('genre', this.genres);
    }

    this.service.advanced(params).subscribe(res => {
      this.result = res.map(el => new Band(el));
      if (this.result.length) {
        this.bandSearchEvent.emit(this.result);
      } else {
        this.noResultEvent.emit('bands');
      }
    }, error1 => console.error(error1));
  }


  addName() {
    this.names += (this.newName + ',');
    this.newName = '';
  }

  addNoName() {
    this.nonames += (this.newNoName + ',');
    this.newNoName = '';
  }

  addCountry() {
    this.countries += (this.newCountry + ',');
    this.newCountry = '';
  }

  addNoCountry() {
    this.nocountries += (this.newNoCountry + ',');
    this.newNoCountry = '';
  }

  addGenre() {
    this.genres += (this.newGenre + ',');
    this.newGenre = '';
  }

  clear() {
    this.nonames =
      this.names =
        this.newGenre =
          this.newNoCountry =
            this.newCountry =
              this.newGenre =
                this.newNoName =
                  this.newName =
                    this.genres =
                      this.nocountries =
                        this.countries = '';
    this.yearBefore = this.yearAfter = undefined;
    this.result = [];
    this.clearEvent.emit();
  }
}
