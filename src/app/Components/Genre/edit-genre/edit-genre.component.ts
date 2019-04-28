import { Component, OnInit } from '@angular/core';
import { GenreService } from 'src/app/Services/genre.service';

@Component({
  selector: 'app-edit-genre',
  templateUrl: './edit-genre.component.html',
  styleUrls: ['./edit-genre.component.css']
})
export class EditGenreComponent implements OnInit {

  constructor(private genreService: GenreService) { }

  ngOnInit() {
  }

}
