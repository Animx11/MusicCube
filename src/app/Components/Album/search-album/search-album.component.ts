import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { AlbumService } from "src/app/Services/album.service";
import { Album } from "src/app/Class/Album";

@Component({
  selector: "app-search-album",
  templateUrl: "./search-album.component.html",
  styleUrls: ["./search-album.component.css"]
})
export class SearchAlbumComponent implements OnInit {
  private searchOn: boolean;

  albums$: Observable<Album[]>;
  private searchTerms = new Subject<string>();

  @Output() albumEvent = new EventEmitter<Album>();

  constructor(private albumService: AlbumService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.albums$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.albumService.getByAlbumName(term))
    );
  }

  toggleSearch() {
    this.searchOn = !this.searchOn;
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(album: Album) {
    this.toggleSearch();
    this.albumEvent.emit(album);
  }
}
