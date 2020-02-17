import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Song} from '../../../Class/Song';
import {SongService} from '../../../Services/song.service';

@Component({
  selector: 'app-advanced-song',
  templateUrl: './advanced-song.component.html',
  styleUrls: ['./advanced-song.component.css']
})
export class AdvancedSongComponent implements OnInit {

  constructor(private service: SongService) { }
  @Output() songSearchEvent = new EventEmitter<Song[]>();
  ngOnInit() {
    const map: Map<string, string> = new Map<string, string>();
    map.set('title', 'love,life');
    this.service.advanced(map).subscribe(res => console.log(res));
  }

}
