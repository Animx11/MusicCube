import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../Services/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  userName: string;
  password: string;

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.userName = '';
    this.password = '';

  }

  signIn() {

  }

}
