import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { AlbumService } from '../../../Services/album.service';
import {Album} from '../../../Class/Album';
import {Band} from '../../../Class/Band';
import {Song} from '../../../Class/Song';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { FavoriteListsService } from 'src/app/Services/favorite-lists.service';
import { Rate } from 'src/app/Class/Rate';
import { CommentClass } from 'src/app/Class/CommentClass';
import { RateService } from 'src/app/Services/rate.service';
import { CommentService } from 'src/app/Services/comment.service';

@Component({
  selector: 'app-display-album',
  templateUrl: './display-album.component.html',
  styleUrls: ['./display-album.component.css']
})
export class DisplayAlbumComponent implements OnInit {

  album: Album;
  bands: Band[];
  songs: Song[];

  rate: Rate;
  comment: CommentClass;
  allComments: CommentClass[];

  private isLogged: boolean;
  private isFavorite: boolean;

  private isRated: boolean;

  private selectOption: string;

  private commentContent: string;
  userName: string;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private albumService: AlbumService,
    private tokenStorage: TokenStorageService,
    private favoriteListsService: FavoriteListsService,
    private rateService: RateService,
    private commentService: CommentService) { }

  ngOnInit() {
    this.getAlbum();
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
    this.favoriteListsService.existAlbumInUserFavorites(this.tokenStorage.getUsername(), id).subscribe(
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
    this.rateService.getByUserNameAndAlbumId(this.userName, id).subscribe(
      res => {
        console.log('This album was rated by user');
        this.rate = new Rate(res);
        this.isRated = true;
        this.selectOption = this.rate.getRate().toString();
      },
      err => {
        console.log('User hasn\'t rated this album yet');
        this.isRated = false;
        this.selectOption = '0';
      }
    );
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

    } else if (!this.isRated){
      this.rateService.createAlbumRate(this.userName, id, parseInt(this.selectOption)).subscribe(
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

  toFavorite() {
    const id = +this.route.snapshot.paramMap.get('id');
    if(this.isFavorite) {
      this.favoriteListsService.deleteAlbumToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Album succesfully deleted from favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    } else {
      this.favoriteListsService.addAlbumToFavorites(this.tokenStorage.getUsername(), id).subscribe(
        res => {
          console.log("Album succesfully added to favorite");
        },
        err => {
          window.alert("Error has occured");
        }
      );
    }
  }


  cleanComment() {
    this.commentContent = '';
  }

  sendComment() {
    const id = +this.route.snapshot.paramMap.get('id');

    if(this.commentContent.length > 2) {
      this.comment = new CommentClass();
      this.comment.setCommentContent(this.commentContent);
      this.comment.setCommentDate(new Date());
      this.comment.setAlbum(this.album);
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
    this.commentService.getByAlbumId(id).subscribe(res => {
      console.log('display-album-component comments, received: ', res);
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

}
