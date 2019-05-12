import { Component, OnInit } from "@angular/core";
import { LocalizationService } from "src/app/Services/localization.service";
import { Localization } from "src/app/Class/Localization";

@Component({
  selector: "app-delete-localization",
  templateUrl: "./delete-localization.component.html",
  styleUrls: ["./delete-localization.component.css"]
})
export class DeleteLocalizationComponent implements OnInit {
  private localizations: Localization[];

  constructor(private localizationService: LocalizationService) {}

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.localizationService.list().subscribe(res => {
      console.log("delete-localization-component recieved:");
      console.log(res);
      this.localizations = res.map(el => new Localization(el));
    });
  }

  delete(id: number) {
    this.localizationService.delete(id).subscribe(res => console.log(res));
  }
}
