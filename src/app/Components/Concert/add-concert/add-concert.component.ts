import { Component, OnInit } from '@angular/core';
import { ConcertService } from 'src/app/Services/concert.service';

@Component({
  selector: 'app-add-concert',
  templateUrl: './add-concert.component.html',
  styleUrls: ['./add-concert.component.css']
})
export class AddConcertComponent implements OnInit {

  constructor(private concertService: ConcertService) { }

  ngOnInit() {
  }

}
