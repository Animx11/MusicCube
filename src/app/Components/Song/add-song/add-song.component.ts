import { Component, OnInit } from '@angular/core';
import { SongService } from 'src/app/Services/song.service';

@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})
export class AddSongComponent implements OnInit {

  constructor(private songService: SongService) { }

  ngOnInit() {
  }

}
