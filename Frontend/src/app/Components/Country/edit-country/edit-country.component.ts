import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Country } from 'src/app/Class/Country';
import { CountryService } from 'src/app/Services/country.service';
import { EditConcertDetailsComponent } from '../../Concert/edit-concert/edit-concert-details/edit-concert-details.component';

@Component({
  selector: 'app-edit-country',
  templateUrl: './edit-country.component.html',
  styleUrls: ['./edit-country.component.css']
})
export class EditCountryComponent implements OnInit {

  private selectedCountry: Country;
  private isEditSelected: boolean;

  private countryName: string;
  private code: string;

  private country: Country;

  constructor(private countryService: CountryService) {}

  ngOnInit() {
    this.isEditSelected = false;
  }

  countryEventHandler($event) {
    this.selectedCountry = $event;
    this.countryName = this.selectedCountry.countryName;
    this.code = this.selectedCountry.code;
    this.isEditSelected = true;
  }
  searchEventHandler($event) {
    this.selectedCountry = null;
  }

  delete() {
    this.countryService.delete(this.selectedCountry.id, this.selectedCountry.countryName).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occured');
      }
    );
  }

  update() {
    if(this.countryName === this.selectedCountry.countryName && this.code === this.selectedCountry.code) {
      window.alert('You need to do some changes before update');
    } else {
      this.country = new Country();
      this.country.id = this.selectedCountry.id;
      this.country.setCountryName(this.countryName);
      this.country.setCode(this.code);
      this.countryService.edit(this.country).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occured');
        }
      );
    }
  }

  reset() {
    this.isEditSelected = false;
    this.selectedCountry = null;
    this.countryName = '';
    this.code = '';
  }

}
