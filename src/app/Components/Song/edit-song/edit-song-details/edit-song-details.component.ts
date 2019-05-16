import { Component, OnInit, Input } from "@angular/core";
import { Song } from "src/app/Class/Song";
import { SongService } from "src/app/Services/song.service";

@Component({
  selector: "app-edit-song-details",
  templateUrl: "./edit-song-details.component.html",
  styleUrls: ["./edit-song-details.component.css"],
})
export class EditSongDetailsComponent implements OnInit {
  @Input() song: Song;

  constructor(private songService: SongService) {}

  ngOnInit() {}

  save(): void {
    this.songService.edit(this.song).subscribe(res => console.log(res));
    this.song = null;
  }

  delete(): void {
    this.songService.delete(this.song.getId()).subscribe(res => {
      console.log(res);
      this.song = null;
    });
  }
}
