import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './Components/User/sign-in/sign-in.component';
import { SignUpComponent } from './Components/User/sign-up/sign-up.component';
import { AdminPanelComponent } from './Components/User/admin-panel/admin-panel.component';
import { SongCRUDComponent } from './Components/Song/song-crud/song-crud.component';

const ROUTES: Routes = [

  // Main Routes

  { path: 'app', component: AppComponent },
  { path : 'AdminPanel', component : AdminPanelComponent},

  // User

  { path : 'SignIn', component : SignInComponent },
  { path : 'SignUp', component : SignUpComponent },

  // Song

  { path : 'Song', component : SongCRUDComponent},

  { path : '', redirectTo : '', pathMatch : 'full'}

];

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    AdminPanelComponent,
    SongCRUDComponent,

  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

export class YourAppModule {
}
