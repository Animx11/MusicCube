import { Component, OnInit } from '@angular/core';
import { GenreService } from 'src/app/Services/genre.service';

@Component({
  selector: 'app-delete-genre',
  templateUrl: './delete-genre.component.html',
  styleUrls: ['./delete-genre.component.css']
})
export class DeleteGenreComponent implements OnInit {

  constructor(private genreService: GenreService) { }

  ngOnInit() {
  }

}
