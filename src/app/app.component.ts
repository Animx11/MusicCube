import { Component, Input, EventEmitter } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { template } from '@angular/core/src/render3';
import { Subscription } from 'rxjs';
import { SignInComponent } from './Components/User/sign-in/sign-in.component';
import { EventListener } from '@angular/core/src/debug/debug_node';
import { TestBed } from '@angular/core/testing';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'MusicCube';
  @Input("log") logged: boolean;

  constructor(private cookieService: CookieService) {

  }

  test() {
    console.log('isHere');
  }

}
