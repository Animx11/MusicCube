import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/Class/Country';
import { CountryService } from 'src/app/Services/country.service';

@Component({
  selector: 'app-add-country',
  templateUrl: './add-country.component.html',
  styleUrls: ['./add-country.component.css']
})
export class AddCountryComponent implements OnInit {

  country: Country;
  countryName: string;
  code: string;

  constructor(private countryService: CountryService) {}

  ngOnInit() {
    this.countryName = '';
    this.code = '';
    this.country = new Country();
  }

  addCountry() {
    if (this.countryName === '' || this.code === '') {
      window.alert('Incomplete input');
    } else {
      this.country.setCountryName(this.countryName);
      this.country.setCode(this.code);
      this.countryService.create(this.country).subscribe(
        res => {
          console.log('add-country-component received:');
          console.log(res);
          window.alert('Country added');
          this.ngOnInit();
        },
        err => {
          /* Trzeba dorobić error handlingi w backu i tutaj */
          console.error(err);
          window.alert('Error occurred');
        }
      );
    }
  }
}
