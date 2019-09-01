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

  private genre: Genre;
  private selectedGenre: Genre;

  private origin: Country;
  private genreName: string;
  private creationTime: string;
  private aboutGenre: string;

  private isOriginSelected: boolean;
  private isOriginClicked: boolean;
  private isEditSelected: boolean;

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
    if(this.genre.getOrigin() != null) {
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
        window.alert('Error has occured');
      }
    );
  }
/*
  update() {
    if(this.cityName === this.selectedCity.cityName && this.city.getCountry() === this.selectedCity.country) {
      window.alert('You need to do some changes before update');
    } else {
      this.city.id = this.selectedCity.id;
      this.city.setCityName(this.cityName);
      this.cityService.edit(this.city).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occured');
        }
      );
    }
  }
*/
  reset() {
    this.isEditSelected = false;
    this.selectedGenre = null;
    this.genre = new Genre();
    this.genreName = '';
    this.creationTime = '';
    this.aboutGenre = '';
  }

}
