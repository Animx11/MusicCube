import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './Services/token-storage.service';
import { this_url } from './Services/API_URL';


const thisURL = this_url;

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
      this.roles.every(role =>{
        if(role === 'ROLE_ADMIN'){
          this.authority = 'admin';
          return false;
        }
        else if(role === 'ROLE_MOD'){
          this.authority = 'mod';
          return false;
        }
        else{
          this.authority = 'user';
          return true;
        }
      });
    }
  }
  signOut()  {
    window.sessionStorage.clear();
    window.location.assign(thisURL);

  }
}
