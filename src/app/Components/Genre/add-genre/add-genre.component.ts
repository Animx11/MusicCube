import { Component, OnInit } from "@angular/core";
import { GenreService } from "src/app/Services/genre.service";
import { Genre } from "src/app/Class/Genre";

@Component({
  selector: "app-add-genre",
  templateUrl: "./add-genre.component.html",
  styleUrls: ["./add-genre.component.css"]
})
export class AddGenreComponent implements OnInit {
  private genre: Genre;

  constructor(private genreService: GenreService) {}

  ngOnInit() {}
}
