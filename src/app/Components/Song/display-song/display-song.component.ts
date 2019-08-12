import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { SongService } from '../../../Services/song.service';
import {Song} from '../../../Class/Song';

@Component({
  selector: 'app-display-song',
  templateUrl: './display-song.component.html',
  styleUrls: ['./display-song.component.css']
})
export class DisplaySongComponent implements OnInit {

  song: Song;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private songService: SongService) { }

  ngOnInit() {
    this.getSong();
  }

  private getSong() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.songService.getById(id).subscribe(
      res => {
        this.song = new Song(res);
        console.log('display-song-component received: ', res);
      },
        err => console.error(err));
  }
}
