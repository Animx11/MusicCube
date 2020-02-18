import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ArtistService } from '../../../Services/artist.service';
import {Artist} from '../../../Class/Artist';
import {ArtistActivity} from '../../../Class/ArtistActivity';
import {ArtistActivityService} from '../../../Services/artist-activity.service';
import {ArtistActivityDisplay} from '../../../Class/ArtistActivityDisplay';
import { Rate } from 'src/app/Class/Rate';
import { CommentClass } from 'src/app/Class/CommentClass';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';
import { RateService } from 'src/app/Services/rate.service';
import { CommentService } from 'src/app/Services/comment.service';

@Component({
  selector: 'app-display-artist',
  templateUrl: './display-artist.component.html',
  styleUrls: ['./display-artist.component.css']
})
export class DisplayArtistComponent implements OnInit {

  artist: Artist;
  activities: ArtistActivity[];
  displays: ArtistActivityDisplay[] = [];

  rate: Rate;
  comment: CommentClass;
  allComments: CommentClass[];

   isLogged: boolean;
   isFavorite: boolean;
   isRated: boolean;

   selectOption: string;

   commentContent: string;
  userName: string;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private artistService: ArtistService,
    private artistInBandService: ArtistActivityService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService,
    private rateService: RateService,
    private commentService: CommentService) { }

  ngOnInit() {
    this.getArtist();
    this.getComments();
    this.isLogged = false;
    this.commentContent = '';
    this.userName = this.tokenStorage.getUsername();
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.checkIfIsFavorite();
      this.checkIfIsRated();
    }
  }

  private checkIfIsFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.favoriteListsService.existArtistInUserFavorites(this.userName, id).subscribe(
      res => {
        this.isFavorite = res;
      },
      err => {
        console.error(err);
      }
    );
  }

  private checkIfIsRated() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.rateService.getByUserNameAndArtistId(this.userName, id).subscribe(
      res => {
        console.log('This artist was rated by user');
        this.rate = new Rate(res);
        this.isRated = true;
        this.selectOption = this.rate.getRate().toString();
      },
      err => {
        console.log('User hasn\'t rated this artist yet');
        this.isRated = false;
        this.selectOption = '0';
      }
    );
  }

  setScore() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (this.isRated && this.selectOption === '0') {
      this.rateService.delete(this.rate.getId()).subscribe(
        res => {
          console.log('Score was deleted');
          this.isRated = false;
        },
        err => {
          console.error('Error has occurred');
        }
      );
    } else if (!this.isRated && this.selectOption === '0') {

    } else if (!this.isRated) {
      this.rateService.createArtistRate(this.userName, id, parseInt(this.selectOption)).subscribe(
        res => {
          this.rate = new Rate(res);
          this.isRated = true;
          console.log('New score was set');
        },
        err => {
          console.error('Error has occurred');
        }
      );
    } else if (this.isRated) {
      this.rateService.edit(this.rate.getId(), parseInt(this.selectOption)).subscribe(
        res => {
          this.rate.setRate(parseInt(this.selectOption));
          console.log('Score has been changed');
        },
        err => {
          console.error('Error has occurred');
        }
      );
    }
  }

  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (this.isFavorite) {
      this.favoriteListsService.deleteArtistToFavorites(this.userName, id).subscribe(
        res => {
          console.log('Artist succesfully deleted from favorite');
        },
        err => {
          console.error('Error has occurred');
        }
      );
    } else {
      this.favoriteListsService.addArtistToFavorites(this.userName, id).subscribe(
        res => {
          console.log('Artist succesfully added to favorite');
        },
        err => {
          console.error('Error has occurred');
        }
      );
    }
  }

  cleanComment() {
    this.commentContent = '';
  }

  sendComment() {
    const id = +this.route.snapshot.paramMap.get('id');

    if (this.commentContent.length > 2) {
      this.comment = new CommentClass();
      this.comment.setCommentContent(this.commentContent);
      this.comment.setCommentDate(new Date());
      this.comment.setArtist(this.artist);
      this.comment.setWasEdited(false);
      this.commentService.create(this.comment, this.userName).subscribe(
        res => {
          console.log('Comment was successfully created');
          window.location.reload();
        },
        err => {
          window.alert('Error has occured');
        }
      );
    }
  }


  getComments() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.commentService.getByArtistId(id).subscribe(res => {
      console.log('display-artist-component comments, received: ', res);
      this.allComments = res.map(el => new CommentClass(el));
    },
    err => console.error(err));
  }

  deleteComment(commentId: number) {
    const id = +this.route.snapshot.paramMap.get('id');
    this.commentService.delete(commentId).subscribe(
      res => {
        console.log('Comment was successfully deleted');
        window.location.reload();
      },
      err => {
        window.alert('Error has occured');
      }
    );
  }

  private getArtist() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.artistService.getById(id).subscribe(
      res => {
        console.log('display-artist-component received: ', res);
        this.artist = new Artist(res);
        this.getBands();
      },
      err => console.error(err));
  }
  private getBands() {
    this.artistInBandService.getByArtistId(this.artist.id).subscribe(res => {
      console.log('display-artist-component received: ', res);
      this.activities = res.map(el => new ArtistActivity(el));
      this.handleActivities();
    },
    err => console.error(err));
  }

  handleActivities() {
    const presentIds = [];
    this.activities.forEach(el => {
      const periodString = ArtistActivityDisplay.buildPeriodString(el);

      const index = presentIds.indexOf(el.band.id);
      if (index >= 0) {
        this.displays[index].addToPeriods(periodString);
        if (el.isActive) {
          this.displays[index].isCurrent();
        }
      } else {
        const aad = new ArtistActivityDisplay(
          this.artist.id,
          this.artist.stageName,
          el.band.id,
          el.band.bandName
        );
        presentIds.push(el.band.id);
        aad.addToPeriods(periodString);
        if (el.isActive) {
          aad.isCurrent();
        }
        this.displays.push(aad);
      }
    });
  }
}
