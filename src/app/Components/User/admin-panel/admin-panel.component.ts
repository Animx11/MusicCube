import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  isSongClicked: boolean;
  isBandClicked: boolean;
  isAlbumClicked: boolean;
  isArtistClicked: boolean;
  isConcertClicked: boolean;
  isGenreClicked: boolean;
  isInstrumentClicked: boolean;
  isCityClicked: boolean;
  showHead: boolean;

  constructor() { }

  ngOnInit() {
    this.isSongClicked = false;
    this.isBandClicked = false;
    this.isAlbumClicked  = false;
    this.isArtistClicked = false;
    this.isConcertClicked = false;
    this.isGenreClicked = false;
    this.isInstrumentClicked = false;
    this.isCityClicked = false;
    this.showHead = false;
  }


  switchValue(clicked: boolean): boolean {
    this.resetValue();
    this.showHead = true;
    return !clicked;
  }

  resetValue() {
    this.isSongClicked = false;
    this.isBandClicked = false;
    this.isAlbumClicked  = false;
    this.isArtistClicked = false;
    this.isConcertClicked = false;
    this.isGenreClicked = false;
    this.isInstrumentClicked = false;
    this.isCityClicked = false;
    this.showHead = false;
  }

}
