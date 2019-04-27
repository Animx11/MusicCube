import { Component, OnInit } from '@angular/core';
import { BandService } from 'src/app/Services/band.service';

@Component({
  selector: 'app-edit-band',
  templateUrl: './edit-band.component.html',
  styleUrls: ['./edit-band.component.css']
})
export class EditBandComponent implements OnInit {

  constructor(private bandService: BandService) { }

  ngOnInit() {
  }

}
