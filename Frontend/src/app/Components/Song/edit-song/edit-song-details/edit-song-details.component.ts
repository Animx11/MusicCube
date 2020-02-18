import { Component, OnInit, Input } from '@angular/core';
import { Song } from 'src/app/Class/Song';
import { SongAuthorship } from 'src/app/Class/SongAuthorship';
import { SongInstrument } from 'src/app/Class/SongInstrument';
import { SongService } from 'src/app/Services/song.service';
import { SongAuthorshipService } from 'src/app/Services/song-authorship.service';
import { SongInstrumentService } from 'src/app/Services/song-instrument.service';

@Component({
  selector: 'app-edit-song-details',
  templateUrl: './edit-song-details.component.html',
  styleUrls: ['./edit-song-details.component.css']
})
export class EditSongDetailsComponent implements OnInit {

  @Input() song: Song;
  authorList: SongAuthorship[];
  instrumentList: SongInstrument[];
  removedAuthors: SongAuthorship[];
  removedSongIns: SongInstrument[];
  newAuthors: SongAuthorship[];
  newSongIns: SongInstrument[];
  oldAuthors: SongAuthorship[];

  constructor(
    private songService: SongService,
    private songAuthorshipService: SongAuthorshipService,
    private songInstrumentService: SongInstrumentService
  ) {}

  ngOnInit() {
    this.removedAuthors = [];
    this.removedSongIns = [];
    this.newAuthors = [];
    this.newSongIns = [];
    this.oldAuthors = [];
  }
  ngOnChanges() {
    if (this.song) { this.loadAuthorsAndInstruments(); }
  }

  save(): void {
    // ZAPISZ AUTORÓW
    this.removedAuthors.forEach(el => {
      if (el.id !== 0) { this.songAuthorshipService.delete(el.id).subscribe(); }
    });
    this.newAuthors.forEach(el => {
      this.songAuthorshipService.create(el).subscribe();
    });
    this.authorList.forEach(el => {
      if (this.newAuthors.indexOf(el) === -1) {
        this.songAuthorshipService.edit(el).subscribe();
      }
    });
    // ZAPISZ INSTRUMENTY
    this.removedSongIns.forEach(el => {
      if (el.id !== 0) { this.songInstrumentService.delete(el.id).subscribe(); }
    });
    this.newSongIns.forEach(el => {
      this.songInstrumentService.create(el).subscribe();
    });
    // ZAPISZ PIOSENKĘ
    this.songService.edit(this.song).subscribe(res => console.log(res));
    this.reset();
  }

  delete(): void {
    this.instrumentList.forEach(el => {
      if (el.id !== 0) { this.songInstrumentService.delete(el.id).subscribe(); }
    });
    this.authorList.forEach(el => {
      if (el.id !== 0) { this.songAuthorshipService.delete(el.id).subscribe(); }
    });
    this.songService.delete(this.song.id).subscribe(res => {
      console.log(res);
      this.reset();
    });
  }

  loadAuthorsAndInstruments() {
    this.songAuthorshipService.getBySongId(this.song.id).subscribe(
      res => {
        this.authorList = res.map(el => new SongAuthorship(el));
        console.log('edit-song-details-component received song authorships');
      },
      err => console.error(err)
    );
    this.songInstrumentService.getBySongId(this.song.id).subscribe(
      res => {
        this.instrumentList = res.map(el => new SongInstrument(el));
        console.log('edit-song-details-component received song instruments');
      },
      err => console.error(err)
    );
  }

  removeAuthor(authorship: SongAuthorship) {
    const index = this.authorList.indexOf(authorship);
    if (index === -1) {
      return;
    }
    if (this.newAuthors.indexOf(authorship) === -1) {
      this.authorList.splice(index, 1);
      this.removedAuthors.push(authorship);
    } else {
      this.newAuthors.splice(this.newAuthors.indexOf(authorship), 1);
      this.authorList.splice(index, 1);
    }
  }
  removeInstrument(songInstrument: SongInstrument) {
    const index = this.instrumentList.indexOf(songInstrument);
    if (index > -1) {
      this.instrumentList.splice(index, 1);
      this.removedSongIns.push(songInstrument);
    }
  }
  instrumentEventHandler($event) {
    let exists = false;
    let removed = false;
    let index = -1;

    this.instrumentList.forEach(el => {
      if (el.getInstrument().id === $event.id) { exists = true; }
    });
    this.removedSongIns.forEach(el => {
      if (el.getInstrument().id === $event.id) {
        removed = true;
        index = this.removedSongIns.indexOf(el);
      }
    });

    if (exists) {
      window.alert('This instrument is alredy included.');
    } else if (removed) {
      this.instrumentList = this.instrumentList.concat(
        this.removedSongIns.splice(index, 1)
      );
    } else {
      let newIns: SongInstrument;
      newIns = new SongInstrument();
      newIns.setSong(this.song);
      newIns.setInstrument($event);
      this.instrumentList.push(newIns);
      this.newSongIns.push(newIns);
    }
  }
  personEventHandler($event) {
    let exists = false;
    let removed = false;
    let index = -1;

    this.authorList.forEach(el => {
      if (el.getAuthor().id === $event.id) { exists = true; }
    });
    this.removedAuthors.forEach(el => {
      if (el.getAuthor().id === $event.id) {
        removed = true;
        index = this.removedAuthors.indexOf(el);
      }
    });

    if (exists) {
      window.alert('This author is alredy included.');
    } else if (removed) {
      this.authorList = this.authorList.concat(
        this.removedAuthors.splice(index, 1)
      );
    } else {
      let newAuth: SongAuthorship;
      newAuth = new SongAuthorship();
      newAuth.setSong(this.song);
      newAuth.setAuthor($event);
      this.authorList.push(newAuth);
      this.newAuthors.push(newAuth);
    }
  }
  reset(): void {
    this.song = null;
    this.removedAuthors = [];
    this.removedSongIns = [];
    this.newAuthors = [];
    this.newSongIns = [];
    this.oldAuthors = [];
  }


/*
 @Input() private song: Song;

 private songName: string;
 private songLengthSeconds: number;
 private authorList: SongAuthorship[];
 private authorship: SongAuthorship;
 private instrumentList: SongInstrument[];
 private songInstrument: SongInstrument;

 private isBandClicked: boolean;
 private isAlbumClicked: boolean;
 private isGenreClicked: boolean;
 private isAuthorClicked: boolean;
 private isInstrumentClicked: boolean;

 private isBandSelected: boolean;
 private isAlbumSelected: boolean;
 private isGenreSelected: boolean;



 constructor(
   private songService: SongService,
   private songAuthorshipService: SongAuthorshipService,
   private songInstrumentService: SongInstrumentService
 ) {}

 ngOnInit() {
   this.songName = '';
   this.authorList = [];
   this.instrumentList = [];
   this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
   this.isBandSelected = this.isAlbumSelected = this.isGenreSelected = false;
 }

 resetClicked(){
   this.isBandClicked = this.isAlbumClicked = this.isGenreClicked = this.isAuthorClicked = this.isInstrumentClicked = false;
 }

 searchValue(value: boolean): boolean {
   this.resetClicked();
   return !value;
 }

 bandEventHandler($event: any) {
   this.song.setBand($event);
   this.isBandSelected = true;
   this.isBandClicked = false;
 }
 albumEventHandler($event: any) {
   this.song.setAlbum($event);
   this.isAlbumSelected = true;
   this.isAlbumClicked = false;
 }
 genreEventHandler($event: any) {
   this.song.setGenre($event);
   this.isGenreSelected = true;
   this.isGenreClicked = false;
 }
 personEventHandler($event: any) {
   this.authorship = new SongAuthorship();
   this.authorship.setAuthor($event);
   this.authorList.push(this.authorship);
   this.isAuthorClicked = false;

 }
 instrumentEventHandler($event: any) {
   this.songInstrument = new SongInstrument();
   this.songInstrument.setInstrument($event);
   this.instrumentList.push(this.songInstrument);
   console.log($event);
   console.log(this.instrumentList);
   this.isInstrumentClicked = false;

 }

 addSong() {
   if (this.songName === '' || this.songLengthSeconds === 0) {
     window.alert('Dane są niekompletne i/lub nieprawidłowe');
   } else {
     this.song.setSongName(this.songName);
     this.song.setSongLengthSeconds(this.songLengthSeconds);

     this.songService.create(this.song).subscribe(
       res => {
         console.log('add-song-component received:');
         console.log(res);
         this.song = new Song(res);
         // DODAWANIE AUTORÓW
         this.authorList.forEach(el => {
           el.setSong(this.song);
           this.songAuthorshipService.create(el).subscribe(
             res => {
               console.log('add-song-component received:');
               console.log(res);
             },
             err => {
               console.error(err);
             }
           );
         });
         // DODAWANIE INSTRUMENTÓW
         this.instrumentList.forEach(el => {
           el.setSong(this.song);
           this.songInstrumentService.create(el).subscribe(
             res => {
               console.log('add-song-component received:');
               console.log(res);
             },
             err => console.error(err)
           );
         });
         window.alert('Dodano nową piosenke');
         this.ngOnInit();
         // window.location.assign(`${thisURL}AdminPanel`);
       },
       err => {
         if (err.status === 409) {
           window.alert('Podana piosenkam istnieje w bazie danych');
         } else {
           window.alert('Nie udało połączyć się z serwerem');
         }
       }
     );
   }
 }
 removeAuthor(authorship: SongAuthorship) {
   const index = this.authorList.indexOf(authorship);
   if (index > -1) { this.authorList.splice(index, 1); }
 }

 removeInstrument(instrument: SongInstrument) {
   const index = this.instrumentList.indexOf(instrument);
   if (index > -1) { this.instrumentList.splice(index, 1); }
 }

 */
}
