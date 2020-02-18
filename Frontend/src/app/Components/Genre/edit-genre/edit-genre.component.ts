import { Component, OnInit } from '@angular/core';
import { GenreService } from 'src/app/Services/genre.service';
import { Country } from 'src/app/Class/Country';
import { Genre } from 'src/app/Class/Genre';

@Component({
  selector: 'app-edit-genre',
  templateUrl: './edit-genre.component.html',
  styleUrls: ['./edit-genre.component.css']
})
export class EditGenreComponent implements OnInit {

  genre: Genre;
  selectedGenre: Genre;

  origin: Country;
  genreName: string;
  creationTime: string;
  aboutGenre: string;

  isOriginSelected: boolean;
  isOriginClicked: boolean;
  isEditSelected: boolean;

  constructor(private genreService: GenreService) {}

  ngOnInit() {
    this.genreName = '';
    this.creationTime = '';
    this.aboutGenre = '';
    this.isOriginClicked = false;
    this.isOriginSelected = false;
    this.genre = new Genre();
  }

  genreEventHandler($event) {
    this.selectedGenre = $event;
    this.genreName = this.selectedGenre.genreName;
    this.creationTime = this.selectedGenre.creationDate;
    this.aboutGenre = this.selectedGenre.aboutGenre;
    this.isEditSelected = true;
    this.isOriginClicked = false;
    this.isOriginSelected = false;
    this.genre.setOrigin(this.selectedGenre.origin);
    if (this.genre.getOrigin() != null) {
      this.isOriginSelected = true;
    }
  }
  searchEventHandler($event) {
    this.selectedGenre = null;
  }


  searchOrigin() {
    this.isOriginClicked = true;
  }

  countryEventHandler($event: any) {
    this.genre.setOrigin($event);
    this.isOriginClicked = false;
    this.isOriginSelected = true;
  }


  delete() {
    this.genreService.delete(this.selectedGenre.id).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occurred');
      }
    );
  }

  update() {
    if (this.genreName === this.selectedGenre.genreName && this.genre.getOrigin() === this.selectedGenre.origin && this.creationTime === this.selectedGenre.creationDate && this.aboutGenre === this.selectedGenre.aboutGenre) {
      window.alert('You need to do some changes before update');
    } else {
      this.genre.id = this.selectedGenre.id;
      this.genre.setGenreName(this.genreName);
      this.genre.setCreationDate(this.creationTime);
      this.genre.setAboutGenre(this.aboutGenre);
      this.genreService.edit(this.genre).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occurred');
        }
      );
    }
  }

  reset() {
    this.isEditSelected = false;
    this.selectedGenre = null;
    this.genre = new Genre();
    this.genreName = '';
    this.creationTime = '';
    this.aboutGenre = '';
  }

}
