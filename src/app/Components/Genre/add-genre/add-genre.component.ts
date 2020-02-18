import { Component, OnInit } from '@angular/core';
import { GenreService } from 'src/app/Services/genre.service';
import { Genre } from 'src/app/Class/Genre';
import { Country } from 'src/app/Class/Country';

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {

  genre: Genre;

  origin: Country;
  genreName: string;
  creationTime: string;
  aboutGenre: string;

  isOriginSelected: boolean;
  isOriginClicked: boolean;

  constructor(private genreService: GenreService) {}

  ngOnInit() {
    this.genreName = '';
    this.creationTime = '';
    this.isOriginClicked = false;
    this.isOriginSelected = false;
    this.genre = new Genre();
    this.aboutGenre = '';
  }

  searchOrigin() {
    this.isOriginClicked = true;
  }

  countryEventHandler($event: any) {
    this.genre.setOrigin($event);
    this.isOriginClicked = false;
    this.isOriginSelected = true;
  }

  addGenre() {
    if (this.genreName === '') {
      window.alert('Incomplete input');
    } else {
      this.genre.setGenreName(this.genreName);
      this.genre.setCreationDate(this.creationTime);
      this.genre.setAboutGenre(this.aboutGenre);
      this.genreService.create(this.genre).subscribe(
        res => {
          console.log('add-genre-component received:');
          console.log(res);
          window.alert('Genre added');
          this.ngOnInit();
        },
        err => {
          console.error(err);
          if (err.status === 409) {
            window.alert(`Genre ${this.genreName} already exists.`);
          } else {
            window.alert('Error occurred');
          }
        }
      );
    }
  }
}
