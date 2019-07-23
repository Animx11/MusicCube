import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/Class/Users';
import { UserService } from 'src/app/Services/user.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { userInfo } from 'os';
import { SignIn } from 'src/app/Class/user/SignIn';
import { this_url } from 'src/app/Services/API_URL';

const thisUrl = this_url;

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  userName: string;
  oldPassword: string;
  newPassword: string;
  repeatPassword: string;
  newEmail: string;
  user: Users;

  
  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {

    this.userName = '';
    this.oldPassword = '';
    this.newPassword = '';
    this.repeatPassword = '';
    this.newEmail = '';
  }

  changeUsername() {
    if(this.tokenStorageService.getUsername() === this.userName){
      window.alert('New username must be different from old one');
    }
    else{

      this.userService.takeUserInfo(this.tokenStorageService.getUsername()).subscribe(
        res => {
          this.user = new Users(res);
          this.user.setUserName(this.userName);
          this.userService.changeUser(this.user).subscribe(res =>
            {
              this.tokenStorageService.signOut();
              window.location.replace(thisUrl);
            },
            err => {
              window.alert('This username is taken');
            })

      });
    }

  }

  changePassword() {
      if(this.oldPassword == '' || this.newPassword == '' || this.repeatPassword == ''){
        window.alert('None of password entity cannot by blanc');
      }
      else if(this.oldPassword == this.newPassword){
        window.alert('New and old password must be different');
      }
      else if(this.newPassword != this.repeatPassword){
        window.alert('New and repeated password are different');
      }
      else if(this.newPassword.length < 6){
        window.alert('Password is too short');
      }
      else{
        this.userService.takeUserInfo(this.tokenStorageService.getUsername()).subscribe(
          res => {
            this.user = new Users(res);
            this.user.setPassword(this.newPassword);
            this.userService.changeUserPassword(this.user, this.oldPassword).subscribe(res =>
              {
                this.tokenStorageService.signOut();
                window.location.replace(thisUrl);
              },
              err => {
                if(err == 409){
                 window.alert('Old password doesnt match');
               }
              })
  
        });
      }

  }

  changeEmail() {

    this.userService.takeUserInfo(this.tokenStorageService.getUsername()).subscribe(
      res => {
        if(this.newEmail !== ''){
        this.user = new Users(res);
        if(this.newEmail !== this.user.getEmail()){
          this.user.setEmail(this.newEmail);
          this.userService.changeUser(this.user).subscribe(res =>
            {
              window.location.reload();
            },
            err => {
              window.alert('This email is taken');
            })
          }
          else{
            window.alert('This is your actual email');
          }
        }
        else{
          window.alert('Email cannot be blank');
        }
      });
    }
  

}
