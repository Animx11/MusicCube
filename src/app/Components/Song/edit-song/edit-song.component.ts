import { Component, OnInit } from '@angular/core';
import { SongService } from 'src/app/Services/song.service';

@Component({
  selector: 'app-edit-song',
  templateUrl: './edit-song.component.html',
  styleUrls: ['./edit-song.component.css']
})
export class EditSongComponent implements OnInit {

  constructor(private songService: SongService) { }

  ngOnInit() {
  }

}