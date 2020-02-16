import { Component, OnInit, Input } from '@angular/core';

import { Artist } from 'src/app/Class/Artist';
import { ArtistService } from 'src/app/Services/artist.service';
import { Person } from 'src/app/Class/Person';
import { PersonService } from 'src/app/Services/person.service';
import { ArtistInstrument } from 'src/app/Class/ArtistInstrument';
import { ArtistInstrumentService } from 'src/app/Services/artist-instrument.service';
import { ArtistActivity } from 'src/app/Class/ArtistActivity';
import { ArtistActivityService } from 'src/app/Services/artist-activity.service';

@Component({
  selector: 'app-add-artist',
  templateUrl: './add-artist.component.html',
  styleUrls: ['./add-artist.component.css']
})
export class AddArtistComponent implements OnInit {
  private person: Person;
  private artist: Artist;
  private firstNames: string;
  private lastName: string;
  private stageName: string;
  private birthDate: Date;
  private deathDate: Date;
  private isArtist: boolean;
  private isBirthPlaceClicked: boolean;
  private isBirthPlaceSelected: boolean;

  private role: string;
  private roles: string[];

  private isBandClicked: boolean;

  private isInstrumentClicked: boolean;
  private isArtistActivityClicked: boolean;

  private instrumentList: ArtistInstrument[];
  private artistInstrument: ArtistInstrument;

  private artistActivityList: ArtistActivity[];
  private artistActivity: ArtistActivity;

  constructor(
    private artistService: ArtistService,
    private personService: PersonService,
    private artistInstrumentService: ArtistInstrumentService,
    private artistActivityService: ArtistActivityService) {}

  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = '';
    this.person = new Person();
    this.birthDate = this.deathDate = null;
    this.isArtist = false;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
    this.isBandClicked = false;
    this.instrumentList = [];
    this.artistActivityList = [];

  }

  searchBirthPlace(){
    this.isBirthPlaceClicked = true;
  }

  cityEventHandler($event: any) {
    this.person.setOrigin($event);
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = true;
  }

  instrumentEventHandler($event: any) {
    this.artistInstrument = new ArtistInstrument();
    this.artistInstrument.setInstrument($event);
    this.instrumentList.push(this.artistInstrument);
    console.log($event);
    console.log(this.instrumentList);
    this.isInstrumentClicked = false;

  }

  bandEventHandler($event: any) {
    this.artistActivity = new ArtistActivity();
    this.artistActivity.setBand($event);
    this.artistActivityList.push(this.artistActivity);
    console.log($event);
    console.log(this.instrumentList);
    this.isBandClicked = false;

  }


  resetClicked() {
    this.isInstrumentClicked = false;
    this.isBandClicked = false;
  }

  searchValue(value: boolean): boolean {
    this.resetClicked();
    return !value;
  }

  add() {
    if (
      this.firstNames === '' ||
      this.lastName === '' ||
      this.birthDate == null ||
      this.person.getOrigin() === null 
    ) {
      window.alert('Incomplete input');
    } else {
      this.person.setFirstNames(this.firstNames);
      this.person.setLastName(this.lastName);
      this.person.setBirthDate(this.birthDate);
      this.person.setDeathDate(this.deathDate);
      if(this.isArtist){
        this.artist = new Artist(this.person);
        this.artist.setStageName(this.stageName);
        this.artistService.create(this.artist).subscribe(
          res => {
            console.log('add-artist-component received artist:');
            console.log(res);
            window.alert('Artist added');

            // Dodawanie instrumentów
            
            this.instrumentList.forEach(el => {
              el.setArtist(res);
              this.artistInstrumentService.create(el).subscribe(
                res => {
                  console.log('add-song-component received:');
                  console.log(res);
                },
                err => {
                  err => console.error(err);
                }
              );
            });

            // Dodawanie zespołów

            this.artistActivityList.forEach(
              el => {
                el.setArtist(res);
                this.artistActivityService.create(el).subscribe(
                  res => {
                    console.log('add-song-component received:');
                    console.log(res);
                  },
                  err => {
                    err => console.error(err);
                  }
                );
              }
            );

            this.ngOnInit();
          },
          err => {
            window.alert('Error occured');
            console.error(err);
          }
        );

      }
      else{
        this.personService.create(this.person).subscribe(
          res => {
            console.log('add-artist-component received person:');
            console.log(res);
            window.alert('Person added');
            this.ngOnInit();
          },
          err => {
            window.alert('Error occured');
            console.error(err);
          }
        );
    }
    }
  }

  addRole(artistActivity: ArtistActivity){
    if(artistActivity.tempRole !== '') {
      this.roles = artistActivity.getRoles();
      this.roles.push(artistActivity.tempRole);
      artistActivity.setRoles(this.roles);
      console.log(this.roles);
      console.log(artistActivity.getRoles());
      artistActivity.tempRole = '';
      this.roles = [];
    }
  }


  removeInstrument(instrument: ArtistInstrument) {
    const index = this.instrumentList.indexOf(instrument);
    if (index > -1) { this.instrumentList.splice(index, 1); }
  }

  removeArtistActivity(activity: ArtistActivity) {
    const index = this.artistActivityList.indexOf(activity);
    if (index > -1) { this.artistActivityList.splice(index, 1); }
  }

  removeRole(artistActivity: ArtistActivity, role: string) {
    this.roles = artistActivity.getRoles();
    const index = this.roles.indexOf(role, 0);
    if (index > -1) {
      this.roles.splice(index, 1);
    }
    artistActivity.setRoles(this.roles);
    this.roles = [];

  }

}
