import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/Services/location.service';
import { Location } from 'src/app/Class/Location';

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {

  private country: string;
  private city: string;
  private address: string;
  private location: Location;

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.country = '';
    this.city = '';
    this.address = '';
    this.location = new Location();
  }

  addLocation() {
    if (this.country === '' || this.city === '' || this.address === '') {
      window.alert('Dane są niekompletne i/lub nieprawidłowe');
    } else {
      this.location.setCountry(this.country);
      this.location.setCity(this.city);
      this.location.setAddress(this.address);

      this.locationService.create(this.location).subscribe(
        res => {
          console.log(res);
          window.alert('Dodano nową lokalizacje');
        },
        err => {
          if (err.status === 409) {
            window.alert('Podana piosenkam istnieje w bazie danych');
          } else {
            window.alert('Nie udało połączyć się z serwerem');
          }
        }
      );
    }
}

}
