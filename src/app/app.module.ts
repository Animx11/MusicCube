import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

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
import { AddBandComponent } from './Components/Band/add-band/add-band.component';
import { EditBandComponent } from './Components/Band/edit-band/edit-band.component';
import { AddAlbumComponent } from './Components/Album/add-album/add-album.component';
import { EditAlbumComponent } from './Components/Album/edit-album/edit-album.component';
import { AddArtistComponent } from './Components/Artist/add-artist/add-artist.component';
import { EditArtistComponent } from './Components/Artist/edit-artist/edit-artist.component';
import { AddConcertComponent } from './Components/Concert/add-concert/add-concert.component';
import { EditConcertComponent } from './Components/Concert/edit-concert/edit-concert.component';
import { AddGenreComponent } from './Components/Genre/add-genre/add-genre.component';
import { EditGenreComponent } from './Components/Genre/edit-genre/edit-genre.component';
import { AddInstrumentComponent } from './Components/Instrument/add-instrument/add-instrument.component';
import { EditInstrumentComponent } from './Components/Instrument/edit-instrument/edit-instrument.component';
import { AddLocalizationComponent } from './Components/Localization/add-localization/add-localization.component';
import { EditLocalizationComponent } from './Components/Localization/edit-localization/edit-localization.component';
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
import { SearchPersonComponent } from './Components/Person/search-person/search-person.component';
import { SearchInstrumentComponent } from './Components/Instrument/search-instrument/search-instrument.component';
import { DisplaySongComponent } from './Components/Song/display-song/display-song.component';
import { DisplayArtistComponent } from './Components/Artist/display-artist/display-artist.component';
import { DisplayBandComponent } from './Components/Band/display-band/display-band.component';
import { DisplayConcertComponent } from './Components/Concert/display-concert/display-concert.component';
import { DisplayGenreComponent } from './Components/Genre/display-genre/display-genre.component';
import { DisplayLocalizationComponent } from './Components/Localization/display-localization/display-localization.component';
import { DisplayPersonComponent } from './Components/Person/display-person/display-person.component';
import { DisplayAlbumComponent } from './Components/Album/display-album/display-album.component';
import { DisplayInstrumentComponent } from './Components/Instrument/display-instrument/display-instrument.component';
import { SearchEngineMainComponent } from './Components/Search/search-engine-main/search-engine-main.component';

import { httpInterceptorProviders } from './Class/authorisation/AuthInterceptor';


const ROUTES: Routes = [
  // Main Routes

  { path: 'app', component: AppComponent },
  { path: 'AdminPanel', component: AdminPanelComponent },

  // User

  { path: 'SignIn', component: SignInComponent },
  { path: 'SignUp', component: SignUpComponent },

  // Song

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddSong', component: AddSongComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditSong', component: EditSongComponent }]
  },
  {
    path: 'song/:id',
    component: DisplaySongComponent
  },

  // Band

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddBand', component: AddBandComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditBand', component: EditBandComponent }]
  },
  {
    path: 'band/:id',
    component: DisplayBandComponent
  },

  // Album

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddAlbum', component: AddAlbumComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditAlbum', component: EditAlbumComponent }]
  },
  {
    path: 'album/:id',
    component: DisplayAlbumComponent
  },

  // Artist

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddArtist', component: AddArtistComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditArtist', component: EditArtistComponent }]
  },
  {
    path: 'artist/:id',
    component: DisplayArtistComponent
  },

  // Concert

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddConcert', component: AddConcertComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditConcert', component: EditConcertComponent }]
  },
  {
    path: 'concert/:id',
    component: DisplayConcertComponent
  },

  // Genre

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddGenre', component: AddGenreComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditGenre', component: EditGenreComponent }]
  },
  {
    path: 'genre/:id',
    component: DisplayGenreComponent
  },

  // Instrument

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddInstrument', component: AddInstrumentComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'EditInstrument', component: EditInstrumentComponent }]
  },
  {
    path: 'instrument/:id',
    component: DisplayInstrumentComponent
  },

  // Localization

  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [{ path: 'AddLocalization', component: AddLocalizationComponent }]
  },
  {
    path: 'AdminPanel',
    component: AdminPanelComponent,
    children: [
      { path: 'EditLocalization', component: EditLocalizationComponent }
    ]
  },
  {
    path: 'localization/:id',
    component: DisplayLocalizationComponent
  },

  // Search

  {
    path: 'search',
    component: SearchEngineMainComponent
  },

  { path: '', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    AdminPanelComponent,
    AddSongComponent,
    EditSongComponent,
    AddBandComponent,
    EditBandComponent,
    AddAlbumComponent,
    EditAlbumComponent,
    AddArtistComponent,
    EditArtistComponent,
    AddConcertComponent,
    EditConcertComponent,
    AddGenreComponent,
    EditGenreComponent,
    AddInstrumentComponent,
    EditInstrumentComponent,
    AddLocalizationComponent,
    EditLocalizationComponent,
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
    SearchPersonComponent,
    SearchInstrumentComponent,
    DisplaySongComponent,
    DisplayArtistComponent,
    DisplayBandComponent,
    DisplayConcertComponent,
    DisplayGenreComponent,
    DisplayLocalizationComponent,
    DisplayPersonComponent,
    DisplayAlbumComponent,
    DisplayInstrumentComponent,
    SearchEngineMainComponent
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
export class AppModule {}

export class YourAppModule {}
