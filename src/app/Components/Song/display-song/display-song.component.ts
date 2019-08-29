import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { SongService } from '../../../Services/song.service';
import {Song} from '../../../Class/Song';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';
import { RateService } from 'src/app/Services/rate.service';
import { Rate } from 'src/app/Class/Rate';

@Component({
  selector: 'app-display-song',
  templateUrl: './display-song.component.html',
  styleUrls: ['./display-song.component.css']
})
export class DisplaySongComponent implements OnInit {

  song: Song;
  rate: Rate;


  private isLogged: boolean;
  private isFavorite: boolean;
  private isRated: boolean;

  private selectOption: string;

  private id: number;
  

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private songService: SongService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService,
    private rateService: RateService) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.getSong();
    this.isLogged = false;
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.checkIfIsFavorite();
      this.checkIfIsRated();
    }
  }

  private checkIfIsFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.favoriteListsService.existSongInUserFavorites(this.tokenStorage.getUsername(), id).subscribe(
      res => {
        this.isFavorite = res;
      },
      err => {
        console.log(err);
      }
    );
  }

  private checkIfIsRated() {
    const id = +this.route.snapshot.paramMap.get('id'); 
    this.rateService.getByUserNameAndSongId(this.tokenStorage.getUsername(), id).subscribe(
      res => {
        console.log("This song was rated by user");
        this.rate = new Rate(res);
        this.isRated = true;
        this.selectOption = this.rate.getRate().toString();
      },
      err => {
        console.log("User hasn't rated this song yet");
        this.isRated = false;
        this.selectOption = "0";
      }
    );
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

  setScore(){
    const id = +this.route.snapshot.paramMap.get('id');
    if (this.isRated && this.selectOption === "0"){
      this.rateService.delete(this.rate.getId()).subscribe(
        res => {
          console.log("Score was deleted");
          this.isRated = false;
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else if (!this.isRated && this.selectOption === "0") {

    } else if (!this.isRated){
      this.rateService.create(this.tokenStorage.getUsername(), id, parseInt(this.selectOption)).subscribe(
        res => {
          this.rate = new Rate(res);
          this.isRated = true;
          console.log("New score was setted");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else if (this.isRated) {
      this.rateService.edit(this.rate.getId(), parseInt(this.selectOption)).subscribe(
        res => {
          this.rate.setRate(parseInt(this.selectOption));
          console.log("Score has been changed");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    }
  }


  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if(this.isFavorite) {
      this.favoriteListsService.deleteSongToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Song succesfully deleted from favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else {
      this.favoriteListsService.addSongToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Song succesfully added to favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    }
  }
}
