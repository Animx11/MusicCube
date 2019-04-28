import { Component, OnInit } from '@angular/core';
import { ConcertService } from 'src/app/Services/concert.service';

@Component({
  selector: 'app-delete-concert',
  templateUrl: './delete-concert.component.html',
  styleUrls: ['./delete-concert.component.css']
})
export class DeleteConcertComponent implements OnInit {

  constructor(private concertService: ConcertService) { }

  ngOnInit() {
  }

}
