import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './Services/token-storage.service';
import { this_url, this_url_news } from './Utils/API_URL';


const thisURL = this_url;
const thisNewsUrl = this_url_news;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'MusicCube';


  private roles: string[];
  private authority: string;
  private isLogged: boolean;

 
  constructor(private tokenStorage: TokenStorageService) { }
 
  ngOnInit() {
    this.isLogged = false;
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.forEach(role => {
        if(role === 'ROLE_ADMIN'){
          this.authority = 'admin';
        }
        else if(role === 'ROLE_MOD' && this.authority !== 'admin'){
          this.authority = 'mod';
        }
        else if(role === 'ROLE_USER' && this.authority !== 'admin' && this.authority !== 'mod'){
          this.authority = 'user';
        }
      });
 
    }
  }

  signOut() {
    this.isLogged = false;
    window.sessionStorage.clear();
    window.location.assign(thisNewsUrl);

  }
}
