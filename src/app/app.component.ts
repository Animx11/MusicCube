import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './Services/token-storage.service';
import { this_url } from './Utils/API_URL';


const thisURL = this_url;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'MusicCube';


  roles: string[];
  authority: string;
  isLogged: boolean;


  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.isLogged = false;
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.forEach(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
        } else if (role === 'ROLE_MOD' && this.authority !== 'admin') {
          this.authority = 'mod';
        } else if (role === 'ROLE_USER' && this.authority !== 'admin' && this.authority !== 'mod') {
          this.authority = 'user';
        }
      });

    }
  }

  signOut() {
    this.isLogged = false;
    window.sessionStorage.clear();
    window.location.assign(thisURL);

  }
}
