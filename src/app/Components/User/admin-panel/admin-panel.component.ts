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
  isLocationClicked: boolean;

  constructor() { }

  ngOnInit() {
    this.isSongClicked = false;
    this.isBandClicked = false;
    this.isAlbumClicked  = false;
    this.isArtistClicked = false;
    this.isConcertClicked = false;
    this.isGenreClicked = false;
    this.isInstrumentClicked = false;
    this.isLocationClicked = false;
  }


  switchValue(clicked: boolean): boolean {
    this.resetValue();
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
    this.isLocationClicked = false;
  }

}
