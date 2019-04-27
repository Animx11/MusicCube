import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/Services/location.service';

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent implements OnInit {

  constructor(private locationService: LocationService) { }

  ngOnInit() {
  }

}
