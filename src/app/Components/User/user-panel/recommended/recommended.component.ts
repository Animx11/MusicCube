import { Component, OnInit } from '@angular/core';
import {Band} from '../../../../Class/Band';
import {BandService} from '../../../../Services/band.service';
import {TokenStorageService} from '../../../../Services/token-storage.service';
import {lifecycleHookToNodeFlag} from '@angular/compiler/src/view_compiler/provider_compiler';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {
  bands: Band[];
  limit: number;

  constructor(private bandService: BandService, private tokenStorageService: TokenStorageService) {
    this.limit = 10;
  }

  ngOnInit() {
    this.getBands();
  }

  getBands() {
    this.bandService.getRecommended(this.tokenStorageService.getUsername(), this.limit ? this.limit : 10)
      .subscribe(res => {
        console.log('recommended bands:', res);
        this.bands = res.map(el => new Band(el));
      },
        error1 => console.error(error1));
  }

  showLess() {
    this.limit -= 10;
    this.getBands();
  }
  showMore() {
    this.limit += 10;
    this.getBands();
  }
}
