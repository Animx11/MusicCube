import { Component, OnInit, Input } from "@angular/core";

import { Artist } from "src/app/Class/Artist";
import { ArtistService } from "src/app/Services/artist.service";

@Component({
  selector: "app-add-artist",
  templateUrl: "./add-artist.component.html",
  styleUrls: ["./add-artist.component.css"]
})
export class AddArtistComponent implements OnInit {
  private artist: Artist;
  private firstNames: string;
  private lastName: string;
  private stageName: string;
  private birthDate: Date;

  constructor(private artistService: ArtistService) {}

  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = "";
    this.artist = new Artist();
    this.birthDate = null;
  }


  addArtist() {
    if (
      this.firstNames === "" ||
      this.lastName === "" ||
      this.stageName === "" ||
      this.birthDate == null
    )
      window.alert("Incomplete input");
    else {
      this.artist.setFirstNames(this.firstNames);
      this.artist.setLastName(this.lastName);
      this.artist.setStageName(this.stageName);
      this.artist.setBirthDate(this.birthDate);

      this.artistService.create(this.artist).subscribe(
        res => {
          console.log("add-artist-component received:");
          console.log(res);
          window.alert("Artist added");
        },
        err => {
          window.alert("Error occured");
          console.error(err);
        }
      );
    }
  }
}
