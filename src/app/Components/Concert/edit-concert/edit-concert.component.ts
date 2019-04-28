import { Component, OnInit } from '@angular/core';
import { ConcertService } from 'src/app/Services/concert.service';

@Component({
  selector: 'app-edit-concert',
  templateUrl: './edit-concert.component.html',
  styleUrls: ['./edit-concert.component.css']
})
export class EditConcertComponent implements OnInit {

  constructor(private concertService: ConcertService) { }

  ngOnInit() {
  }

}
