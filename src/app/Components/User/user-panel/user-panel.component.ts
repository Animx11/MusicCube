import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {

  userName: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
  birthDate: Date;

  constructor() { }

  ngOnInit() {

    this.userName = '';
    this.password = '';
    this.email = '';
    this.firstName = '';
    this.lastName = '';
    this.birthDate = null;

  }

}
