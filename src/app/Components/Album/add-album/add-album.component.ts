import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/Services/album.service';
import { Album } from 'src/app/Class/Album';

@Component({
  selector: 'app-add-album',
  templateUrl: './add-album.component.html',
  styleUrls: ['./add-album.component.css']
})
export class AddAlbumComponent implements OnInit {
  private album: Album;
  private albumName: string;
  private length: number;
  private minutes: number;
  private seconds: number;
  private releaseDate: Date;
  private company: string;
  private covertArtLink: string;
  private type: string;

  constructor(private albumService: AlbumService) {}

  ngOnInit() {
    this.album = new Album();
    this.albumName = '';
    this.minutes = 0;
    this.seconds = 0;
    this.length = 0;
    this.releaseDate = null;
    this.company = "";
    this.covertArtLink = "";
    this.type = "";
  }

  albumLengthInSeconds(): boolean {
    if (this.minutes < 0 || this.seconds < 0 || this.seconds > 59 || this.minutes === 0 && this.seconds === 0) {
      return false;
    } else {
      this.length = 60 * this.minutes + this.seconds;
      return true;
    }
  }

  addAlbum(): void {
    if(!this.albumLengthInSeconds()){
      window.alert("Album lenght is incorect");
    }
    else if (
      this.albumName === '' ||
      this.length === 0 ||
      this.releaseDate == null ||
      this.company === '' ||
      this.type == ''
    )
      window.alert('Incomplete input');
    else {
      this.album.setAlbumName(this.albumName);
      this.album.setAlbumLengthSeconds(this.length);
      this.album.setReleaseDate(this.releaseDate);
      this.album.setCompany(this.company);
      this.album.setCoverArtLink(this.covertArtLink);
      this.album.setType(this.type);
      this.albumService.create(this.album).subscribe(
        res => {
          console.log('add-album-component received:');
          console.log(res);
          window.alert('Album added');
          this.ngOnInit();
        },
        err => {
          window.alert('Error occured');
          console.error(err);
        }
      );
    }
  }
}
