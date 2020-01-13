import { Component, OnInit } from '@angular/core';
import {RankingService} from '../../Services/ranking.service';
import {SongRatingDto} from '../../DTOs/SongRatingDto';

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.component.html',
  styleUrls: ['./rankings.component.css']
})
export class RankingsComponent implements OnInit {
  songRatingDtos: SongRatingDto[];

  constructor(private rankingService: RankingService) { }

  ngOnInit() {
    this.getSongs(5);
  }

  getSongs(size: number) {
    this.rankingService.getTopRatedSongs(size).subscribe(
      res => {
        console.log(res);
        this.songRatingDtos = res.map( el => new SongRatingDto(el));
      },
      error1 => console.error(error1)
    );
  }

}
