import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/Class/Users';
import { UserService } from 'src/app/Services/user.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { userInfo } from 'os';
import { SignIn } from 'src/app/Class/SignIn';
import { this_url } from 'src/app/Services/API_URL';
import { UserAccount } from 'src/app/Class/UserAccount';

const thisUrl = this_url;

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  userName: string;
  password: string;
  oldPassword: string;
  newPassword: string;
  repeatPassword: string;
  newEmail: string;
  user: Users;
  userAccount: UserAccount;

  
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
    else if(this.userName === ''){
      window.alert(`Username cannot be blank`);
    }
    else{
      this.userAccount = new UserAccount();
      this.userAccount.setUserName(this.tokenStorageService.getUsername());
      this.password = window.prompt("Podaj hasło");
      this.userAccount.setPassword(this.password);
      this.userService.changeUserName(this.userName, this.userAccount).subscribe(
        res => {
          this.tokenStorageService.signOut();
          window.location.replace(thisUrl);
        },
        err => {
          window.alert(`This username is taken`);
        }
      )
    }

  }
  changeEmail() {

          this.userAccount = new UserAccount();
          if(this.newEmail === ''){
            window.alert('Email cannot be blank');
          }
          else{
            this.userAccount.setEmail(this.newEmail);
            this.userAccount.setUserName(this.tokenStorageService.getUsername());
            this.password = window.prompt("Podaj hasło");
            this.userAccount.setPassword(this.password);
            this.userService.changeEmail(this.userAccount).subscribe(
              res => {
                window.location.reload();
              },
              err => {
                console.log(err.status);
              }
            );
          }
        }

  changePassword() {
    /*
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
  */
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
      this.userAccount = new UserAccount();
      this.userAccount.setUserName(this.tokenStorageService.getUsername());
      this.userAccount.setPassword(this.oldPassword);
      this.userService.changePassword(this.userAccount, this.newPassword).subscribe(
        res => {
          this.tokenStorageService.signOut();
          window.location.replace(thisUrl);    
        },
        err => {

        }
      );
    }

  }


}