import { Component, OnInit, Output, EventEmitter, SystemJsNgModuleLoader } from '@angular/core';
import { UserService } from '../../../Services/user.service';
import { SignIn } from 'src/app/Class/SignIn';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { this_url } from 'src/app/Utils/API_URL';

const thisURL = this_url;

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  userName: string;
  password: string;
  user: SignIn;
  isLoginFailed = false;

  constructor(private userService: UserService, private tokenStorage: TokenStorageService) {
   }

  ngOnInit() {

    this.userName = '';
    this.password = '';

  }


  signIn() {
    this.user = new SignIn(this.userName, this.password);
    this.userService.signIn(this.user).subscribe(
      res => {
        this.tokenStorage.saveToken(res.token);
        this.tokenStorage.saveUsername(res.userName);
        this.tokenStorage.saveAuthorities(res.authorities);
        this.isLoginFailed = false;

        window.location.assign(thisURL);
      },
      err => {
        console.log(err);
        if (err.status === 401) {
          window.alert('Incorect login and/or password');
        } else {
          window.alert('Connection with server failed');
        }
        this.isLoginFailed = true;
      }
    );
  }


}
