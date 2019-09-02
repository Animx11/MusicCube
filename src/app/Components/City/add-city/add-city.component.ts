import { Component, OnInit } from '@angular/core';
import { CityService } from 'src/app/Services/city.service';
import { City } from 'src/app/Class/City';

@Component({
  selector: 'app-add-city',
  templateUrl: './add-city.component.html',
  styleUrls: ['./add-city.component.css']
})
export class AddCityComponent implements OnInit {

  city: City;
  cityName: string;

  isCountryClicked: boolean;
  isCountrySelected: boolean;

  constructor(private cityService: CityService) { }

  ngOnInit() {
    this.cityName = '';
    this.city = new City();
    this.isCountryClicked = false;
    this.isCountrySelected = false;
  }

  searchCountry() {
    this.isCountryClicked = true;
  }

  countryEventHandler($event: any) {
    this.city.setCountry($event);
    this.isCountrySelected = true;
    this.isCountryClicked = false;
  }

  addCity() {
    if (this.cityName === '') {
      window.alert('Incomplete input');
    } else {
      this.city.setCityName(this.cityName);
      this.cityService.create(this.city).subscribe(
        res => {
          console.log('add-city-component received:');
          console.log(res);
          window.alert('City added');
          this.ngOnInit();
        },
        err => {
          console.error(err);
          window.alert('Error occurred');
        }
      );

    }
  }

}
