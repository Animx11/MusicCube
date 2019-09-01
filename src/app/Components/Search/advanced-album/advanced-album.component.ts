import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Album} from '../../../Class/Album';
import {Song} from '../../../Class/Song';
import {Genre} from '../../../Class/Genre';
import {AlbumService} from '../../../Services/album.service';

@Component({
  selector: 'app-advanced-album',
  templateUrl: './advanced-album.component.html',
  styleUrls: ['./advanced-album.component.css', '../../../app.component.css']
})
export class AdvancedAlbumComponent implements OnInit {

  private song: Song;
  private compName: string;
  private genre: Genre;
  private result: Album[];

  constructor(private service: AlbumService) {
    this.song = null;
    this.compName = '';
    this.genre = null;
    this.result = [];
  }

  @Output() albumSearchEvent = new EventEmitter<Album[]>();

  ngOnInit() {
  }

  resetSong() {
    this.song = null;
  }
  songEvent($event) {
    this.song = new Song($event);
  }
  resetGenre() {
    this.genre = null;
  }
  genreEvent($event) {
    this.genre = new Genre($event);
  }

  search() {
    if ( !(this.song || this.genre || this.compName) ) {
      window.alert('Provide at least one parameter');
    } else {
      this.service.advancedSearch(this.song, this.genre, this.compName).subscribe(
        res => {
          this.result = res.map(el => new Album(el));
          console.log(`Advanced album search received: `, res);
          this.albumSearchEvent.emit(this.result);
        },
        err => {
          console.error(err);
        }
      );
    }
  }
}
