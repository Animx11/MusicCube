import { Component, OnInit } from '@angular/core';
import { UserAccount } from '../../../Class/UserAccount'
import { UserService } from '../../../Services/user.service';
import { this_url } from 'src/app/Services/API_URL';

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
      r => {window.location.assign(thisURL);},
      e => {console.log(e);}
    );

  }

  resetValue() {

    this.userName = '';
    this.password = '';
    this.email = '';
    this.user = new UserAccount();

  }

}
