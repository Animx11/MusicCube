import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/Services/user.service';
import { this_url } from 'src/app/Utils/API_URL';
import { Users } from 'src/app/Class/Users';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { formatDate, DatePipe } from '@angular/common';
import { SELECT_VALUE_ACCESSOR } from '@angular/forms/src/directives/select_control_value_accessor';
import { browser } from 'protractor';
import { UserProfile } from 'src/app/Class/UserProfile';

const thisURL = this_url;
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
  userProfile: UserProfile;


  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {

    this.userService.takeUserProfileInfo(this.tokenStorageService.getUsername()).subscribe(
      res => {
        this.userProfile = new UserProfile(res);
        this.firstName = this.userProfile.getFirstName();
        this.lastName = this.userProfile.getLastName();
        this.aboutUser = this.userProfile.getAboutUser();
        this.birthDate = this.userProfile.getBirthDate();
    });
  }

  updateProfile(): void {

      this.userProfile.setFirstName(this.firstName);
      this.userProfile.setLastName(this.lastName);
      this.userProfile.setAboutUser(this.aboutUser);
      this.userProfile.setBirthDate(this.birthDate);

      this.userService.changeUserProfile(this.userProfile).subscribe(
        res => {
          window.location.reload();
        },
        err => {
          console.log(err.status);
          if (err.status === 400) {
            window.alert('If you see this massage, something unexpected happen, contact with code mainteners');
          } else if (err.status === 401) {
            window.alert('Session expired, try sign in again');
          } else {
            window.alert('Connection with server failed');
          }
        }
      );
  }

}
