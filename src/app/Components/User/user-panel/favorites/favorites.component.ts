import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Song } from 'src/app/Class/Song';
import { Band } from 'src/app/Class/Band';
import { Album } from 'src/app/Class/Album';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { Artist } from 'src/app/Class/Artist';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  isSongListClicked: boolean;
  isAlbumListClicked: boolean;
  isBandListClicked: boolean;
  isArtistListClicked: boolean;

  songList: Set<Song>;
  albumList: Set<Album>;
  bandList: Set<Band>;
  artistList: Set<Artist>;


  pagedSongList: Set<Song>;

  constructor(
    private favoriteListsService: FavoriteListsService,
    private tokenService: TokenStorageService
    ) { }

  ngOnInit() {
    this.isSongListClicked = this.isAlbumListClicked = this.isBandListClicked = this.isArtistListClicked = false;
 
  }

  songFavoriteList() {
    this.resetValue();
    this.isSongListClicked = true;
    this.favoriteListsService.getUserFavoriteSongsByUserName(this.tokenService.getUsername()).subscribe(
      res => {
        this.songList = new Set<Song>(res);
        console.log("Successfully taken song list");
      }, err => {
        window.alert("Error has occured");
      }
    );
  }

  albumFavoriteList() {
    this.resetValue();
    this.isAlbumListClicked = true;
    this.favoriteListsService.getUserFavoriteAlbumsByUserName(this.tokenService.getUsername()).subscribe(
      res => {
        this.albumList = new Set<Album>(res);
        console.log("Successfully taken album list");
      }, err => {
        window.alert("Error has occured");
      }
    );
  }

  bandFavoriteList() {
    this.resetValue();
    this.isBandListClicked = true;
    this.favoriteListsService.getUserFavoriteBandsByUserName(this.tokenService.getUsername()).subscribe(
      res => {
        this.bandList = new Set<Band>(res);
        console.log("Successfully taken band list");
      }, err => {
        window.alert("Error has occured");
      }
    );
  }

  artistFavoriteList() {
    this.resetValue();
    this.isArtistListClicked = true;
    this.favoriteListsService.getUserFavoriteArtistByUserName(this.tokenService.getUsername()).subscribe(
      res => {
        this.artistList = new Set<Artist>(res);
        console.log("Successfully taken artist list");
      }, err => {
        window.alert("Error has occured");
      }
    );
  }

  resetValue(){
    this.isSongListClicked = this.isAlbumListClicked = this.isBandListClicked = this.isArtistListClicked = false;
  }


/*
  private searchOn: boolean;
  private listOn: boolean;
  private pageOn: boolean;
  private pageSize: number;
  private pageNr: number;
  private reachedLastPage: boolean;
  private pageCount: number;

  songs$: Observable<Song[]>;
  songPage: Song[];
  private searchTerms = new Subject<string>();

  @Output() songEvent = new EventEmitter<Song>();
  @Output() searchEvent = new EventEmitter();

  constructor(private songService: SongService) {
  }

  ngOnInit(): void {
    this.pageSize = 10;
    this.pageNr = 0;
    this.pageCount = 0;
    this.songs$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
  }

  toggleSearch() {
    this.songs$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
    this.searchOn = !this.searchOn;
    this.listOn = true;
    this.pageOn = false;
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(song: Song) {
    this.searchOn = false;
    this.listOn = false;
    this.pageOn = false;
    this.songEvent.emit(song);
  }

  getAll() {
    this.searchOn = false;
    this.listOn = false;
    this.pageOn = true;
    this.songService.listPaging(this.pageNr, this.pageSize).subscribe(res => {
      console.log('search-song-component received', res);
      this.songPage = res.content.map(el => new Song(el));
      this.reachedLastPage = res.last;
      this.pageCount = res.totalPages;
    });
    this.searchEvent.emit();
  }

  nextPage() { if (this.reachedLastPage) {
    window.alert('No more data');
  } else {
      this.pageNr++;
      this.getAll();
    }
  }

  prevPage() {
    this.pageNr--;
    this.getAll();
  }

  setSize(size: number) {

    switch (this.pageSize) {
      case 100:
        switch (size) {
          case 50: this.pageNr *= 2; break;
          case 20: this.pageNr *= 5; break;
          case 10: this.pageNr *= 10; break;
          case 5: this.pageNr *= 20;
        }
        break;
      case 50:
        switch (size) {
          case 100: this.pageNr = Math.floor(this.pageNr / 2); break;
          case 20: this.pageNr = Math.floor(this.pageNr * 2.5); break;
          case 10: this.pageNr *= 5; break;
          case 5: this.pageNr *= 10;
        }
        break;
      case 20:
        switch (size) {
          case 100: this.pageNr = Math.floor(this.pageNr / 5); break;
          case 50: this.pageNr = Math.floor(this.pageNr / 2.5); break;
          case 10: this.pageNr *= 2; break;
          case 5: this.pageNr *= 4;
        }
        break;
      case 10:
        switch (size) {
          case 100: this.pageNr = Math.floor(this.pageNr / 10); break;
          case 50:  this.pageNr = Math.floor(this.pageNr / 5); break;
          case 20:  this.pageNr = Math.floor(this.pageNr / 2); break;
          case 5: this.pageNr *= 2;
        }
        break;
      case 5:
        switch (size) {
          case 100: this.pageNr = Math.floor(this.pageNr / 20); break;
          case 50: this.pageNr = Math.floor(this.pageNr / 10); break;
          case 20: this.pageNr = Math.floor(this.pageNr / 4); break;
          case 10: this.pageNr = Math.floor(this.pageNr / 2); break;
        }
    }

    this.pageSize = size;
    this.getAll();
  }
}
*/
}
