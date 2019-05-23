import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { LocalizationService } from '../../../Services/localization.service';
import {Localization} from '../../../Class/Localization';

@Component({
  selector: 'app-display-localization',
  templateUrl: './display-localization.component.html',
  styleUrls: ['./display-localization.component.css']
})
export class DisplayLocalizationComponent implements OnInit {

  localization: Localization;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private localizationService: LocalizationService) { }

  ngOnInit() {
    this.getLocalization();
  }

  private getLocalization() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.localizationService.getById(id).subscribe(
      res => {
        this.localization = new Localization(res);
        console.log('display-localization-component received: ', res);
      },
      err => console.error(err));
  }
}
