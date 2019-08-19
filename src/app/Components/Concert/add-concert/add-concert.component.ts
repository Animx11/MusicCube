import { Component, OnInit, Input } from "@angular/core";

import { Concert } from "src/app/Class/Concert";
import { ConcertService } from "src/app/Services/concert.service";

@Component({
  selector: "app-add-concert",
  templateUrl: "./add-concert.component.html",
  styleUrls: ["./add-concert.component.css"]
})
export class AddConcertComponent implements OnInit {
  private concert: Concert;
  private concertName: string;
  private startTime: string;
  private hour: number;
  private minute: number;


  constructor(
    private concertService: ConcertService,
  ) {}

  ngOnInit() {
    this.concert = new Concert();
    this.concertName = "";
    this.startTime = "";
    this.hour = this.minute = 0;
  }

  addConcert() {
    if (
      this.concertName === "" ||
      this.startTime === ""
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
