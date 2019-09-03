import { Component, OnInit } from '@angular/core';
import { City } from 'src/app/Class/City';
import { CityService } from 'src/app/Services/city.service';
import { Country } from 'src/app/Class/Country';

@Component({
  selector: 'app-edit-city',
  templateUrl: './edit-city.component.html',
  styleUrls: ['./edit-city.component.css']
})
export class EditCityComponent implements OnInit {

  private selectedCity: City;
  private isEditSelected: boolean;

  private city: City;
  private cityName: string;

  private isCountryClicked: boolean;
  private isCountrySelected: boolean;

  constructor(private cityService: CityService) { }

  ngOnInit() {
    this.isEditSelected = false;
    this.city = new City();

  }

  cityEventHandler($event) {
    this.selectedCity = $event;
    this.cityName = this.selectedCity.cityName;
    this.isEditSelected = true;
    this.isCountryClicked = false;
    this.isCountrySelected = false;
    this.city.setCountry(this.selectedCity.country);
  }
  searchEventHandler($event) {
    this.selectedCity = null;
  }

  searchCountry() {
    this.isCountryClicked = true;
  }

  countryEventHandler($event: any) {
    this.city.setCountry($event);
    this.isCountrySelected = true;
    this.isCountryClicked = false;
  }

  delete() {
    this.cityService.delete(this.selectedCity.id).subscribe(
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
    if(this.cityName === this.selectedCity.cityName && this.city.getCountry() === this.selectedCity.country) {
      window.alert('You need to do some changes before update');
    } else {
      this.city.id = this.selectedCity.id;
      this.city.setCityName(this.cityName);
      this.cityService.edit(this.city).subscribe(
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
    this.selectedCity = null;
    this.city = new City();
    this.cityName = '';
  }

}
