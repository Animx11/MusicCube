import { Component, OnInit } from "@angular/core";
import { AlbumService } from "src/app/Services/album.service";
import { Album } from "src/app/Class/Album";

@Component({
  selector: "app-add-album",
  templateUrl: "./add-album.component.html",
  styleUrls: ["./add-album.component.css"],
})
export class AddAlbumComponent implements OnInit {
  private album: Album;
  private albumName: string;
  private length: number;
  private releaseDate: Date;
  private company: string;

  constructor(private albumService: AlbumService) {}

  ngOnInit() {
    this.album = new Album();
    this.albumName = "";
    this.length = 0;
    this.releaseDate = null;
    this.company = "";
  }
  save(): void {
    this.album.setAlbumName(this.albumName);
    this.album.setAlbumLengthSeconds(this.length);
    this.album.setReleaseDate(this.releaseDate);
    this.album.setCompany(this.company);
    this.albumService.create(this.album).subscribe(res => console.log(res));
  }
}
