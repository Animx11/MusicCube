import { Component, OnInit } from '@angular/core';

import { FetchDataService } from '../../../Services/fetch-data.service';

import { this_url } from 'src/app/Utils/API_URL';
import {of} from 'rxjs';

const thisUrl = this_url;

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  // Main

  isBackClicked: boolean;
  isShowRecordClicked: boolean;
  isManageUsersClicked: boolean;
  isFetchDataClicked: boolean;
  showHead: boolean;

  // Song

  isSongClicked: boolean;
  isSongAddClicked: boolean;
  isSongEditClicked: boolean;

  // Band

  isBandClicked: boolean;
  isBandAddClicked: boolean;
  isBandEditClicked: boolean;

  // Album

  isAlbumClicked: boolean;
  isAlbumAddClicked: boolean;
  isAlbumEditClicked: boolean;

  // Person

  isPersonClicked: boolean;
  isPersonAddClicked: boolean;
  isPersonEditClicked: boolean;

  // Concert

  isConcertClicked: boolean;
  isConcertAddClicked: boolean;
  isConcertEditClicked: boolean;

  // Genre

  isGenreClicked: boolean;
  isGenreAddClicked: boolean;
  isGenreEditClicked: boolean;

  // Instrument

  isInstrumentClicked: boolean;
  isInstrumentAddClicked: boolean;
  isInstrumentEditClicked: boolean;

  // InstrumentType

  isInstrumentTypeClicked: boolean;
  isInstrumentTypeAddClicked: boolean;
  isInstrumentTypeEditClicked: boolean;

  // City

  isCityClicked: boolean;
  isCityAddClicked: boolean;
  isCityEditClicked: boolean;

  // Country

  isCountryClicked: boolean;
  isCountryAddClicked: boolean;
  isCountryEditClicked: boolean;

  constructor(private fetchDataService: FetchDataService) { }

  ngOnInit() {

    this.isBackClicked = false;
    this.isShowRecordClicked = false;
    this.isManageUsersClicked = false;
    this.isFetchDataClicked = false;
    this.showHead = false;

    this.isSongClicked = false;
    this.isSongAddClicked = false;
    this.isSongEditClicked = false;

    this.isBandClicked = false;
    this.isBandAddClicked = false;
    this.isBandEditClicked = false;

    this.isAlbumClicked = false;
    this.isAlbumAddClicked = false;
    this.isAlbumEditClicked = false;

    this.isPersonClicked = false;
    this.isPersonAddClicked = false;
    this.isPersonEditClicked = false;

    this.isConcertClicked = false;
    this.isConcertAddClicked = false;
    this.isConcertEditClicked = false;

    this.isGenreClicked = false;
    this.isGenreAddClicked = false;
    this.isGenreEditClicked = false;

    this.isInstrumentClicked = false;
    this.isInstrumentAddClicked = false;
    this.isInstrumentEditClicked = false;

    this.isInstrumentTypeClicked = false;
    this.isInstrumentTypeAddClicked = false;
    this.isInstrumentTypeEditClicked = false;

    this.isCityClicked = false;
    this.isCityAddClicked = false;
    this.isCityEditClicked = false;

    this.isCountryClicked = false;
    this.isCountryAddClicked = false;
    this.isCountryEditClicked = false;

  }

  // Resets

  resetMainValue() {
    this.isBackClicked = false;
    this.isShowRecordClicked = false;
    this.isManageUsersClicked = false;
    this.isFetchDataClicked = false;
    this.showHead = false;
  }

  resetRecordValue() {
    this.isSongClicked = false;
    this.isBandClicked = false;
    this.isAlbumClicked  = false;
    this.isPersonClicked = false;
    this.isConcertClicked = false;
    this.isGenreClicked = false;
    this.isInstrumentClicked = false;
    this.isInstrumentTypeClicked = false;
    this.isCityClicked = false;
    this.isCountryClicked = false;
  }

  resetOptionValue() {
    this.isSongAddClicked = false;
    this.isSongEditClicked = false;
    this.isBandAddClicked = false;
    this.isBandEditClicked = false;
    this.isAlbumAddClicked = false;
    this.isAlbumEditClicked = false;
    this.isPersonAddClicked = false;
    this.isPersonEditClicked = false;
    this.isConcertAddClicked = false;
    this.isConcertEditClicked = false;
    this.isGenreAddClicked = false;
    this.isGenreEditClicked = false;
    this.isInstrumentAddClicked = false;
    this.isInstrumentEditClicked = false;
    this.isInstrumentTypeAddClicked = false;
    this.isInstrumentTypeEditClicked = false;
    this.isCityAddClicked = false;
    this.isCityEditClicked = false;
    this.isCountryAddClicked = false;
    this.isCountryEditClicked = false;
  }

  resetValues() {
    this.resetMainValue();
    this.resetRecordValue();
    this.resetOptionValue();
  }

  // Switch

  switchMainValue(clicked: boolean): boolean {
    return !clicked;
  }


  switchRecordValue(clicked: boolean): boolean {
    this.resetOptionValue();
    this.resetRecordValue();
    clicked = false;
    this.showHead = true;
    return !clicked;
  }

  switchOptionValue(clicked: boolean): boolean {
    this.resetOptionValue();
    clicked = false;
    return !clicked;
  }

  userPanel() {
    window.location.replace(thisUrl + '/UserPanel');
  }

  // Fetch data
  fetchData(offset: string, count: string) {
    console.log('fetchin');
    this.fetchDataService.fetch(offset, count).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.error(err);
        if (err.status === 400) {
          window.alert(err.error);
        }
        if (err.status === 200) {
          window.alert(err.error.text);
        }
      }
    );
  }

}
