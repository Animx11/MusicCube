import { Component, Input, EventEmitter } from '@angular/core';
import { TokenStorageService } from './Services/token-storage.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  private title = 'MusicCube';
  private roles: string[];
  private authority: string;

  constructor(private tokenStorage: TokenStorageService) {}
  

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
    }
  }
  

}
