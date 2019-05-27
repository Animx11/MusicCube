import { Component, OnInit, Input } from "@angular/core";

import { Concert } from "src/app/Class/Concert";
import { Localization } from "src/app/Class/Localization";
import { ConcertService } from "src/app/Services/concert.service";
import { LocalizationService } from "src/app/Services/localization.service";

@Component({
  selector: "app-add-concert",
  templateUrl: "./add-concert.component.html",
  styleUrls: ["./add-concert.component.css"]
})
export class AddConcertComponent implements OnInit {
  private concert: Concert;
  private concertName: string;
  private startTime: string;
  private localization: Localization;
  private hour: number;
  private minute: number;

  private localizationSelected: boolean;
  private localizationList: Localization[];

  constructor(
    private concertService: ConcertService,
    private localizationService: LocalizationService
  ) {}

  ngOnInit() {
    this.concert = new Concert();
    this.concertName = "";
    this.startTime = "";
    this.localization = null;
    this.hour = this.minute = 0;
  }

  selectLocalization() {
    this.localizationService.list().subscribe(res => {
      console.log("add-concert-component received:");
      console.log(res);
      this.localizationList = res.map(el => new Localization(el));
    });
    this.localizationSelected = true;
  }

  addLocalization(origin: Localization) {
    this.concert.setLocalization(origin);
    this.localizationSelected = false;
  }
  addConcert() {
    if (
      this.concertName === "" ||
      this.startTime === "" ||
      this.concert.getLocalization() == null
    )
      window.alert("Incomplete input");
    else {
      this.concert.setConcertName(this.concertName);
      this.concert.setStartTime(
        new Date(`${this.startTime}T${this.hour}:${this.minute}:00`)
      );

      this.concertService.create(this.concert).subscribe(
        res => {
          console.log("add-concert-component received:");
          console.log(res);
          window.alert("Concert added");
        },
        err => {
          window.alert("Error occured");
          console.error(err);
        }
      );
    }
  }
}
