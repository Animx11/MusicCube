import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { SongService } from 'src/app/Services/song.service';
import { Song } from 'src/app/Class/Song';

@Component({
  selector: 'app-search-song',
  templateUrl: './search-song.component.html',
  styleUrls: ['./search-song.component.css']
})
export class SearchSongComponent implements OnInit {

  songs$: Observable<Song[]>;
  songPage: Song[];
  private searchTerms = new Subject<string>();

  public isToEditSelected: boolean;

  @Output() songEvent = new EventEmitter<Song>();
  @Output() searchEvent = new EventEmitter();

  constructor(private songService: SongService) {
  }

  ngOnInit(): void {
    this.isToEditSelected = false;
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
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(song: Song) {
    this.isToEditSelected = true;
    this.songEvent.emit(song);
  }
/*
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
  */
}
