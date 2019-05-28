import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/Services/user.service';
import { this_url } from 'src/app/Services/API_URL';
import { Users } from 'src/app/Class/Users';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { formatDate, DatePipe } from '@angular/common';
import { SELECT_VALUE_ACCESSOR } from '@angular/forms/src/directives/select_control_value_accessor';
import { browser } from 'protractor';

const thisURL = this_url
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  firstName: string;
  lastName: string;
  birthDate: Date;
  aboutUser: string;
  user: Users;


  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {

    this.userService.takeUserInfo(this.tokenStorageService.getUsername()).subscribe(
      res => {
        this.user = new Users(res);
        this.firstName = this.user.getFirstName();
        this.lastName = this.user.getLastName();
        this.aboutUser = this.user.getAboutUser();
        this.birthDate = this.user.getBirthDate();

    });
  }

  updateProfile(): void {
      this.user.setFirstName(this.firstName);
      this.user.setLastName(this.lastName);
      this.user.setAboutUser(this.aboutUser);
      this.user.setBirthDate(this.birthDate);

      this.userService.changeUserProfile(this.user).subscribe(
        res => {
          window.location.reload();

        },
        err => {
          window.alert("Nie udało połączyć się z serwerem");
        }
      );
  }

}
