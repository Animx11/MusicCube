import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/Services/location.service';
import { Location } from 'src/app/Class/Location';

@Component({
  selector: 'app-edit-location',
  templateUrl: './edit-location.component.html',
  styleUrls: ['./edit-location.component.css']
})
export class EditLocationComponent implements OnInit {

  constructor(private locationService: LocationService) { }

  city: string;
  locationAll: Location[];
  location: Location;

  ngOnInit() {
    this.city = '';
  }

  findByCity(){
    this.locationService.getByCity(this.city).subscribe(res => {
      this.location = new Location(res);
      res.map(el => this.location = new Location(el));
    });
  }

  findAllLocation(){
    this.locationService.list().subscribe(res => {
      //res.map(el => this.locationAll = new Location());
    });
  }

}
