import { Component, OnInit } from '@angular/core';
import { UserAccount } from '../../../Class/UserAccount';
import { UserService } from '../../../Services/user.service';
import { this_url } from 'src/app/Utils/API_URL';

const thisURL = this_url;

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  userName: string;
  password: string;
  email: string;
  user: UserAccount;

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.userName = '';
    this.password = '';
    this.email = '';
    this.user = new UserAccount();

  }

  signUp() {

    this.user.setUserName(this.userName);
    this.user.setPassword(this.password);
    this.user.setEmail(this.email);
    this.userService.signUp(this.user).subscribe(
      res => {window.location.assign(thisURL); },
      err => {
        console.log(err);
        if (err.status === 409) {
          window.alert('Username and/or email is taken');
        } else {
          window.alert('Connection with server failed');
        }
      }
    );

  }

  resetValue() {

    this.userName = '';
    this.password = '';
    this.email = '';
    this.user = new UserAccount();

  }

}
