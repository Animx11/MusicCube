import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/Services/location.service';

@Component({
  selector: 'app-delete-location',
  templateUrl: './delete-location.component.html',
  styleUrls: ['./delete-location.component.css']
})
export class DeleteLocationComponent implements OnInit {

  constructor(private locationService: LocationService) { }

  ngOnInit() {
  }

}
