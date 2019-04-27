import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/Services/location.service';

@Component({
  selector: 'app-edit-location',
  templateUrl: './edit-location.component.html',
  styleUrls: ['./edit-location.component.css']
})
export class EditLocationComponent implements OnInit {

  constructor(private locationService: LocationService) { }

  ngOnInit() {
  }

}
