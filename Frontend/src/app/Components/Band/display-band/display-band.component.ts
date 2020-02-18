import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';

import { BandService } from '../../../Services/band.service';
import {Band} from '../../../Class/Band';
import {Album} from '../../../Class/Album';
import {ArtistActivity} from '../../../Class/ArtistActivity';
import {ArtistActivityService} from '../../../Services/artist-activity.service';
import {ArtistActivityDisplay} from '../../../Class/ArtistActivityDisplay';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';
import { Rate } from 'src/app/Class/Rate';
import { CommentClass } from 'src/app/Class/CommentClass';
import { RateService } from 'src/app/Services/rate.service';
import { CommentService } from 'src/app/Services/comment.service';
import {this_url} from '../../../Utils/API_URL';

@Component({
  selector: 'app-display-band',
  templateUrl: './display-band.component.html',
  styleUrls: ['./display-band.component.css']
})
export class DisplayBandComponent implements OnInit {

  band: Band;
  albums: Album[];
  lnp: ArtistActivity[];
  artistDisplays: ArtistActivityDisplay[];
  similarBands: Band[];


  rate: Rate;
  comment: CommentClass;
  allComments: CommentClass[];

  isLogged: boolean;
  isFavorite: boolean;
  isRated: boolean;

  isMainClicked: boolean;
  isLineUpClicked: boolean;
  isDiscographyClicked: boolean;
  isSimilarBandsClicked: boolean;

  selectOption: string;

  commentContent: string;
  userName: string;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private bandService: BandService,
    private activityService: ArtistActivityService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService,
    private rateService: RateService,
    private commentService: CommentService) {
    this.artistDisplays = [];
  }

  ngOnInit() {
    this.getBand();
    this.getComments();
    this.isLogged = false;
    this.commentContent = '';
    this.userName = this.tokenStorage.getUsername();
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.checkIfIsFavorite();
      this.checkIfIsRated();
    }

    this.isMainClicked = true;
    this.isDiscographyClicked = this.isLineUpClicked = this.isSimilarBandsClicked = false;
  }


  private checkIfIsFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.favoriteListsService.existBandInUserFavorites(this.tokenStorage.getUsername(), id).subscribe(
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
    this.rateService.getByUserNameAndBandId(this.userName, id).subscribe(
      res => {
        console.log('This band was rated by user');
        this.rate = new Rate(res);
        this.isRated = true;
        this.selectOption = this.rate.getRate().toString();
      },
      err => {
        console.log('User hasn\'t rated this band yet');
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
      this.rateService.createBandRate(this.userName, id, parseInt(this.selectOption)).subscribe(
        res => {
          this.rate = new Rate(res);
          this.isRated = true;
          console.log('New score was setted');
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

  private getBand() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.bandService.getById(id).subscribe(
      res => {
        console.log('display-band-component received band: ', res);
        this.band = new Band(res);
        this.getAlbums();
        this.getMembers();
        this.getSimilarBands(10);
      },
      err => console.error(err));
  }
  private getAlbums() {
    this.bandService.getBandAlbums(this.band.getId()).subscribe(res => {
      console.log('display-band-component received albums: ', res);
      this.albums = res.map(el => new Album(el));
    },
      err => console.error(err));
  }
  private getMembers() {
    this.activityService.getByBandId(this.band.id).subscribe(
      res => {
        console.log('display-band-component received artists activities: ', res);
        this.lnp = res.map(el => new ArtistActivity(el));
        this.handleActivity();
      },
      err => console.error(err)
    );
  }
  /*
    Puts all activity periods of one artist together, allowing us to display them next to his name
   */
  private handleActivity() {
    const presentIds = [];
    this.lnp.forEach(el => {
      const periodString = ArtistActivityDisplay.buildPeriodString(el);

      const index = presentIds.indexOf(el.artist.id);
      if (index >= 0) {
        this.artistDisplays[index].addToPeriods(periodString);
        if (el.isActive) {
          this.artistDisplays[index].isCurrent();
        }
      } else {
        const aad = new ArtistActivityDisplay(el.artist.id, el.artist.stageName);
        presentIds.push(el.artist.id);
        aad.addToPeriods(periodString);
        if (el.isActive) {
          aad.isCurrent();
        }
        this.artistDisplays.push(aad);
      }
    });
  }

  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (this.isFavorite) {
      this.favoriteListsService.deleteBandToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log('Band successfully deleted from favorites');
        },
        err => {
          window.alert('Error has occurred');
        }
      );
    } else {
      this.favoriteListsService.addBandToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log('Band successfully added to favorites');
        },
        err => {
          window.alert('Error has occurred');
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
      this.comment.setBand(this.band);
      this.comment.setWasEdited(false);
      this.commentService.create(this.comment, this.userName).subscribe(
        res => {
          console.log('Comment was successfully created');
          window.location.reload();
        },
        err => {
          window.alert('Error has occurred');
        }
      );
    }
  }


  getComments() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.commentService.getByBandId(id).subscribe(res => {
      console.log('display-band-component comments, received: ', res);
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
        window.alert('Error has occurred');
      }
    );
  }

  resetValue() {
    this.isMainClicked = this.isLineUpClicked = this.isDiscographyClicked = this.isSimilarBandsClicked = false;
  }

  switchValue(clicked: boolean): boolean {
    this.resetValue();
    clicked = true;
    return clicked;
  }

  private getSimilarBands(limit: number) {
    const id = +this.route.snapshot.paramMap.get('id');
    this.bandService.getSimilar(this.band.getId(), limit).subscribe(res => {
        console.log('display-band-component comments, received: ', res);
        this.similarBands = res.map(el => new Band(el));
      },
      err => console.error(err));
  }

  reload(id: number) {
    window.location.assign(`${this_url}/band/${id}`);
  }
}
