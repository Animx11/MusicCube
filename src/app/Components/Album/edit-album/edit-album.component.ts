import { Component, OnInit } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { debounceTime, distinctUntilChanged, switchMap } from "rxjs/operators";
import { AlbumService } from "src/app/Services/album.service";
import { Album } from "src/app/Class/Album";

@Component({
  selector: "app-edit-album",
  templateUrl: "./edit-album.component.html",
  styleUrls: ["./edit-album.component.css"],
})
export class EditAlbumComponent implements OnInit {
  albums$: Observable<Album[]>;
  private searchTerms = new Subject<string>();
  private selectedAlbum: Album;

  constructor(private albumService: AlbumService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  onSelect(album: Album): void {
    this.selectedAlbum = album;
  }

  ngOnInit(): void {
    this.albums$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.albumService.getByAlbumName(term))
    );
  }
}
