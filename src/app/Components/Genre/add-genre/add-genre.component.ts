import { Component, OnInit } from '@angular/core';
import { GenreService } from 'src/app/Services/genre.service';
import { Genre } from 'src/app/Class/Genre';

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {
  private genre: Genre;
  private genreName: string;
  private creationTime: string;

  constructor(private genreService: GenreService) {}

  ngOnInit() {
    this.genreName = '';
    this.creationTime = '';
    this.genre = new Genre();
  }
  localizationEventHandler($event) {
    this.genre.setOrigin($event);
  }
  addGenre() {
    if (this.genreName === '') {
      window.alert('Incomplete input');
    } else {
      this.genre.setGenreName(this.genreName);
      this.genre.setCreationDate(this.creationTime);
      this.genreService.create(this.genre).subscribe(
        res => {
          console.log('add-genre-component received:');
          console.log(res);
          window.alert('Genre added');
        },
        err => {
          console.error(err);
          window.alert('Error occurred');
        }
      );
    }
  }
}