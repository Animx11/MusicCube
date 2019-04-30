import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {UserService} from '../../../Services/user.service';
import { SimpleToken } from 'src/app/Class/SimpleToken';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {



  userName: string;
  password: string;
  token: SimpleToken;

  constructor(private userService: UserService, private cookieService: CookieService) {
   }

  ngOnInit() {

    this.userName = '';
    this.password = '';

  }


  signIn() {
    this.userService.signIn(this.userName, this.password).subscribe(
      res => {
        console.log(res);
        this.token = new SimpleToken(res);
        this.cookieService.set(this.token.getUserName(), this.token.getCode(), this.token.getExpiryTime());
      }
      );
  }

}
