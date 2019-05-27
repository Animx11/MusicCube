import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { AlbumService } from '../../../Services/album.service';
import {Album} from '../../../Class/Album';
import {Band} from '../../../Class/Band';
import {Song} from '../../../Class/Song';

@Component({
  selector: 'app-display-album',
  templateUrl: './display-album.component.html',
  styleUrls: ['./display-album.component.css']
})
export class DisplayAlbumComponent implements OnInit {

  album: Album;
  bands: Band[];
  songs: Song[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private albumService: AlbumService) { }

  ngOnInit() {
    this.getAlbum();
  }

  private getAlbum() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.albumService.getById(id).subscribe(
      res => {
        this.album = new Album(res);
        console.log('display-album-component received: ', res);
        this.getBands();
        this.getSongs();
      },
      err => console.error(err));
  }
  private getBands() {
    this.albumService.getAlbumBands(this.album.getId()).subscribe(res => {
      console.log('display-album-component received: ', res);
      this.bands = res.map(el => new Band(el));
    },
    err => console.error(err));
  }
  private getSongs() {
    this.albumService.getAlbumSongs(this.album.getId()).subscribe(res => {
      console.log('display-album-component received: ', res);
      this.songs = res.map(el => new Song(el));
    },
    err => console.error(err));
  }
}
