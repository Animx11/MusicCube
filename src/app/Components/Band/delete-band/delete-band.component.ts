import { Component, OnInit } from '@angular/core';
import { BandService } from 'src/app/Services/band.service';

@Component({
  selector: 'app-delete-band',
  templateUrl: './delete-band.component.html',
  styleUrls: ['./delete-band.component.css']
})
export class DeleteBandComponent implements OnInit {

  constructor(private bandService: BandService) { }

  ngOnInit() {
  }

}
