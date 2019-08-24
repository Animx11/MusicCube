import { Component, OnInit } from "@angular/core";
import { GenreService } from "src/app/Services/genre.service";
import { Genre } from "src/app/Class/Genre";
import { Country } from "src/app/Class/Country";

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {

  private genre: Genre;

  private origin: Country;
  private genreName: string;
  private creationTime: string;
  private aboutGenre: string;

  private isOriginClicked: boolean;

  constructor(private genreService: GenreService) {}

  ngOnInit() {
    this.genreName = "";
    this.creationTime = "";
    this.isOriginClicked = false;
    this.genre = new Genre();
  }

  searchOrigin(){
    this.isOriginClicked = true;
  }

  countryEventHandler($event: any) {
    this.genre.setOrigin($event);
    this.isOriginClicked = false;
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
        },
        err => {
          console.error(err);
          window.alert('Error occurred');
        }
      );
    }
  }
}
