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
import { AddSongComponent } from './Components/Song/add-song/add-song.component';
import { EditSongComponent } from './Components/Song/edit-song/edit-song.component';
import { DeleteSongComponent } from './Components/Song/delete-song/delete-song.component';
import { AddBandComponent } from './Components/Band/add-band/add-band.component';
import { EditBandComponent } from './Components/Band/edit-band/edit-band.component';
import { DeleteBandComponent } from './Components/Band/delete-band/delete-band.component';
import { AddAlbumComponent } from './Components/Album/add-album/add-album.component';
import { EditAlbumComponent } from './Components/Album/edit-album/edit-album.component';
import { DeleteAlbumComponent } from './Components/Album/delete-album/delete-album.component';
import { AddArtistComponent } from './Components/Artist/add-artist/add-artist.component';
import { EditArtistComponent } from './Components/Artist/edit-artist/edit-artist.component';
import { DeleteArtistComponent } from './Components/Artist/delete-artist/delete-artist.component';
import { AddConcertComponent } from './Components/Concert/add-concert/add-concert.component';
import { EditConcertComponent } from './Components/Concert/edit-concert/edit-concert.component';
import { DeleteConcertComponent } from './Components/Concert/delete-concert/delete-concert.component';
import { AddGenreComponent } from './Components/Genre/add-genre/add-genre.component';
import { EditGenreComponent } from './Components/Genre/edit-genre/edit-genre.component';
import { DeleteGenreComponent } from './Components/Genre/delete-genre/delete-genre.component';
import { AddInstrumentComponent } from './Components/Instrument/add-instrument/add-instrument.component';
import { EditInstrumentComponent } from './Components/Instrument/edit-instrument/edit-instrument.component';
import { DeleteInstrumentComponent } from './Components/Instrument/delete-instrument/delete-instrument.component';
import { AddLocalizationComponent } from './Components/Localization/add-localization/add-localization.component';
import { EditLocalizationComponent } from './Components/Localization/edit-localization/edit-localization.component';
import { DeleteLocalizationComponent } from './Components/Localization/delete-localization/delete-localization.component';
import { EditLocalizationDetailsComponent } from './Components/Localization/edit-localization/edit-localization-details/edit-localization-details.component';
import { EditAlbumDetailsComponent } from './Components/Album/edit-album/edit-album-details/edit-album-details.component';
import { EditArtistDetailsComponent } from './Components/Artist/edit-artist/edit-artist-details/edit-artist-details.component';
import { EditBandDetailsComponent } from './Components/Band/edit-band/edit-band-details/edit-band-details.component';
import { EditConcertDetailsComponent } from './Components/Concert/edit-concert/edit-concert-details/edit-concert-details.component';
import { EditGenreDetailsComponent } from './Components/Genre/edit-genre/edit-genre-details/edit-genre-details.component';
import { EditInstrumentDetailsComponent } from './Components/Instrument/edit-instrument/edit-instrument-details/edit-instrument-details.component';
import { EditSongDetailsComponent } from './Components/Song/edit-song/edit-song-details/edit-song-details.component';
import { SearchLocalizationComponent } from './Components/Localization/search-localization/search-localization.component';
import { SearchAlbumComponent } from './Components/Album/search-album/search-album.component';
import { SearchSongComponent } from './Components/Song/search-song/search-song.component';
import { SearchBandComponent } from './Components/Band/search-band/search-band.component';
import { SearchGenreComponent } from './Components/Genre/search-genre/search-genre.component';

import { httpInterceptorProviders } from './Class/authorisation/AuthInterceptor';
import { UserPanelComponent } from './Components/User/user-panel/user-panel.component';


const ROUTES: Routes = [

  // Main Routes

  { path: 'app', component: AppComponent },
  { path : 'AdminPanel', component : AdminPanelComponent},
  { path : 'UserPanel', component : UserPanelComponent},

  // User

  { path : 'SignIn', component : SignInComponent },
  { path : 'SignUp', component : SignUpComponent },

  // Song

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddSong', component: AddSongComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditSong', component: EditSongComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteSong', component: DeleteSongComponent }]
  },

  // Band

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddBand', component: AddBandComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditBand', component: EditBandComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteBand', component: DeleteBandComponent }]
  },

  // Album

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddAlbum', component: AddAlbumComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditAlbum', component: EditAlbumComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteAlbum', component: DeleteAlbumComponent }]
  },

  // Artist

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddArtist', component: AddArtistComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditArtist', component: EditArtistComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteArtist', component: DeleteArtistComponent }]
  },

  // Concert

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddConcert', component: AddConcertComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditConcert', component: EditConcertComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteConcert', component: DeleteConcertComponent }]
  },

  // Genre

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddGenre', component: AddGenreComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditGenre', component: EditGenreComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteGenre', component: DeleteGenreComponent }]
  },

  // Instrument

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddInstrument', component: AddInstrumentComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditInstrument', component: EditInstrumentComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteInstrument', component: DeleteInstrumentComponent }]
  },

  // Localization

  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'AddLocalization', component: AddLocalizationComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'EditLocalization', component: EditLocalizationComponent }]
  },
  {
    path: 'AdminPanel', component: AdminPanelComponent,
    children: [{ path: 'DeleteLocalization', component: DeleteLocalizationComponent }]
  },

  { path : '', redirectTo : '', pathMatch : 'full'}

];

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    AdminPanelComponent,
    AddSongComponent,
    EditSongComponent,
    DeleteSongComponent,
    AddBandComponent,
    EditBandComponent,
    DeleteBandComponent,
    AddAlbumComponent,
    EditAlbumComponent,
    DeleteAlbumComponent,
    AddArtistComponent,
    EditArtistComponent,
    DeleteArtistComponent,
    AddConcertComponent,
    EditConcertComponent,
    DeleteConcertComponent,
    AddGenreComponent,
    EditGenreComponent,
    DeleteGenreComponent,
    AddInstrumentComponent,
    EditInstrumentComponent,
    DeleteInstrumentComponent,
    AddLocalizationComponent,
    EditLocalizationComponent,
    DeleteLocalizationComponent,
    EditLocalizationDetailsComponent,
    EditAlbumDetailsComponent,
    EditArtistDetailsComponent,
    EditBandDetailsComponent,
    EditConcertDetailsComponent,
    EditGenreDetailsComponent,
    EditInstrumentDetailsComponent,
    EditSongDetailsComponent,
    SearchLocalizationComponent,
    SearchAlbumComponent,
    SearchSongComponent,
    SearchBandComponent,
    SearchGenreComponent,
    UserPanelComponent,

  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

export class YourAppModule {
}
