import { Component, OnInit } from '@angular/core';

import { SongService } from 'src/app/Services/song.service';

import { Song } from 'src/app/Class/Song';

@Component({
  selector: 'app-edit-song',
  templateUrl: './edit-song.component.html',
  styleUrls: ['./edit-song.component.css']
})
export class EditSongComponent implements OnInit {
  private selectedSong: Song;

  constructor(private songService: SongService) {}

  ngOnInit() {}

  songEventHandler($event) {
    this.selectedSong = $event;
  }
  searchEventHandler($event) {
    this.selectedSong = null;
  }
}
