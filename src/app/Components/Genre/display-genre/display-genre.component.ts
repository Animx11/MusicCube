import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { GenreService } from '../../../Services/genre.service';
import {Genre} from '../../../Class/Genre';

@Component({
  selector: 'app-display-genre',
  templateUrl: './display-genre.component.html',
  styleUrls: ['./display-genre.component.css']
})
export class DisplayGenreComponent implements OnInit {

  genre: Genre;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private genreService: GenreService) { }

  ngOnInit() {
    this.getGenre();
  }

  private getGenre() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.genreService.getById(id).subscribe(
      res => {
        this.genre = new Genre(res);
        console.log('display-genre-component received: ', res);
        if (!this.genre.aboutGenre) {
          this.genre.setAboutGenre(
            'No description was provided for this genre. If you think, you could write some, please contact us.'
          );
        }
      },
      err => console.error(err));
  }
}
