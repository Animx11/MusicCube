import { Component, OnInit } from '@angular/core';
import { SongService } from 'src/app/Services/song.service';

@Component({
  selector: 'app-delete-song',
  templateUrl: './delete-song.component.html',
  styleUrls: ['./delete-song.component.css']
})
export class DeleteSongComponent implements OnInit {

  constructor(private songService: SongService) { }

  ngOnInit() {
  }

}
