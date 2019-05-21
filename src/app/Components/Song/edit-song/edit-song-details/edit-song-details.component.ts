import { Component, OnInit, Input } from "@angular/core";
import { Song } from "src/app/Class/Song";
import { SongAuthorship } from "src/app/Class/SongAuthorship";
import { SongInstrument } from "src/app/Class/SongInstrument";
import { SongService } from "src/app/Services/song.service";
import { SongAuthorshipService } from "src/app/Services/song-authorship.service";
import { SongInstrumentService } from "src/app/Services/song-instrument.service";

@Component({
  selector: "app-edit-song-details",
  templateUrl: "./edit-song-details.component.html",
  styleUrls: ["./edit-song-details.component.css"]
})
export class EditSongDetailsComponent implements OnInit {
  @Input() song: Song;
  authorList: SongAuthorship[];
  instrumentList: SongInstrument[];

  constructor(
    private songService: SongService,
    private songAuthorshipService: SongAuthorshipService,
    private songInstrumentService: SongInstrumentService
  ) {}

  ngOnInit() {}

  save(): void {
    this.songService.edit(this.song).subscribe(res => console.log(res));
    this.song = null;
  }

  delete(): void {
    this.songService.delete(this.song.id).subscribe(res => {
      console.log(res);
      this.song = null;
    });
  }
  loadAuthorsAndInstruments() {
    this.songAuthorshipService.getBySongId(this.song.id).subscribe(
      res => {
        this.authorList = res.map(el => new SongAuthorship(el));
        console.log("edit-song-details-component recieved song authorships");
      },
      err => console.log(err)
    );
    this.songInstrumentService.getBySongId(this.song.id).subscribe(
      res => {
        this.instrumentList = res.map(el => new SongInstrument(el));
        console.log("edit-song-details-component recieved song instruments");
      },
      err => console.log(err)
    );
  }
}
