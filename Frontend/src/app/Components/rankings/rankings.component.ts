import { Component, OnInit } from '@angular/core';
import {RankingService} from '../../Services/ranking.service';
import {SongRatingDto} from '../../DTOs/SongRatingDto';
import {AlbumRatingDto} from '../../DTOs/AlbumRatingDto';
import {BandRatingDto} from '../../DTOs/BandRatingDto';
import {ArtistRatingDto} from '../../DTOs/ArtistRatingDto';

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.component.html',
  styleUrls: ['./rankings.component.css']
})
export class RankingsComponent implements OnInit {
  songRatingDtos: SongRatingDto[];
  artistRatingDtos: ArtistRatingDto[];
  bandRatingDtos: BandRatingDto[];
  albumRatingDtos: AlbumRatingDto[];

  constructor(private rankingService: RankingService) { }

  ngOnInit() {
    this.getSongs(5);
    this.getAlbums(5);
    this.getArtists(5);
    this.getBands(5);
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
  getAlbums(size: number) {
    this.rankingService.getTopRatedAlbums(size).subscribe(
      res => {
        console.log(res);
        this.albumRatingDtos = res.map( el => new AlbumRatingDto(el));
      },
      error1 => console.error(error1)
    );
  }
  getBands(size: number) {
    this.rankingService.getTopRatedBands(size).subscribe(
      res => {
        console.log(res);
        this.bandRatingDtos = res.map( el => new BandRatingDto(el));
      },
      error1 => console.error(error1)
    );
  }
  getArtists(size: number) {
    this.rankingService.getTopRatedArtists(size).subscribe(
      res => {
        console.log(res);
        this.artistRatingDtos = res.map( el => new ArtistRatingDto(el));
      },
      error1 => console.error(error1)
    );
  }
}
