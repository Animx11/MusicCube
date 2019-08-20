import { Component, OnInit, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { PersonService } from 'src/app/Services/person.service';
import { BandService } from 'src/app/Services/band.service';
import { AlbumService } from 'src/app/Services/album.service';
import { SongService } from 'src/app/Services/song.service';
import { GenreService } from 'src/app/Services/genre.service';
import { InstrumentService } from 'src/app/Services/instrument.service';


import { Person } from 'src/app/Class/Person';
import { Band } from 'src/app/Class/Band';
import { Album } from 'src/app/Class/Album';
import { Song } from 'src/app/Class/Song';
import { Genre } from 'src/app/Class/Genre';
import { Instrument } from 'src/app/Class/Instrument';


@Component({
  selector: 'app-search-engine-main',
  templateUrl: './search-engine-main.component.html',
  styleUrls: ['./search-engine-main.component.css']
})
export class SearchEngineMainComponent implements OnInit {

  people$: Observable<Person[]>;
  bands$: Observable<Band[]>;
  albums$: Observable<Album[]>;
  songs$: Observable<Song[]>;
  genres$: Observable<Genre[]>;
  instruments$: Observable<Instrument[]>;
  private searchTerms = new Subject<string>();


  constructor(
    private personService: PersonService,
    private bandService: BandService,
    private albumService: AlbumService,
    private songService: SongService,
    private genreService: GenreService,
    private instrumentService: InstrumentService) { }

  ngOnInit() {
    this.people$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.personService.getByLastName(term))
    );
    this.bands$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.bandService.getByBandName(term))
    );
    this.albums$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.albumService.getByAlbumName(term))
    );
    this.songs$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.songService.getBySongName(term))
    );
    this.genres$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.genreService.getByGenreName(term))
    );
    this.instruments$ = this.searchTerms.pipe(
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((term: string) => this.instrumentService.getByInstrumentName(term))
    );
  }

  search(term: string): void {
      this.searchTerms.next(term);
    }

}
