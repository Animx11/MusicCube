import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

import { Artist } from "src/app/Class/Artist";
import { Localization } from "src/app/Class/Localization";
import { ArtistService } from "src/app/Services/artist.service";
import { LocalizationService } from "src/app/Services/localization.service";

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
  private origin: Localization;
  private birthDate: Date;

  private originSelected: boolean;
  private localizationList: Localization[];

  constructor(
    private localizationService: LocalizationService,
    private artistService: ArtistService
  ) {}

  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = "";
    this.artist = new Artist();
    this.origin = null;
    this.birthDate = null;

    this.originSelected = false;
  }

  selectOrigin() {
    this.localizationService.list().subscribe(res => {
      console.log("add-artist-component recieved:");
      console.log(res);
      this.localizationList = res.map(el => new Localization(el));
    });
    this.originSelected = true;
  }
  addOrigin(origin: Localization) {
    this.artist.setOrigin(origin);
    this.originSelected = false;
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
          console.log("add-artist-component recieved:");
          console.log(res);
          window.alert("Artist added");
        },
        err => {
          window.alert("Error occured");
          console.log(err);
        }
      );
    }
  }
}
