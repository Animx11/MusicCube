import { Component, OnInit } from '@angular/core';
import { ArtistService } from 'src/app/Services/artist.service';
import { PersonService } from 'src/app/Services/person.service';
import { Person } from 'src/app/Class/Person';
import { Artist } from 'src/app/Class/Artist';
import { ArtistInstrumentService } from 'src/app/Services/artist-instrument.service';
import { ArtistInstrument } from 'src/app/Class/ArtistInstrument';
import { ArtistActivity } from 'src/app/Class/ArtistActivity';
import { ArtistActivityService } from 'src/app/Services/artist-activity.service';

@Component({
  selector: 'app-edit-artist',
  templateUrl: './edit-artist.component.html',
  styleUrls: ['./edit-artist.component.css']
})
export class EditArtistComponent implements OnInit {

  person: Person;
  artist: Artist;
  selectedArtist: Artist;
  firstNames: string;
  lastName: string;
  stageName: string;
  birthDate: Date;
  deathDate: Date;
  isArtist: boolean;
  isBirthPlaceClicked: boolean;
  isBirthPlaceSelected: boolean;

  isEditSelected: boolean;

  role: string;
  roles: string[];

  isBandClicked: boolean;

  isInstrumentClicked: boolean;
  isArtistActivityClicked: boolean;

  instrumentList: ArtistInstrument[];
  artistInstrument: ArtistInstrument;

  artistActivityList: ArtistActivity[];
  artistActivity: ArtistActivity;

  toDeleteInstruments: number[];
  toDeleteActivities: number[];

  constructor(
    private artistService: ArtistService,
    private personService: PersonService,
    private artistInstrumentService: ArtistInstrumentService,
    private artistActivityService: ArtistActivityService) {}

  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = '';
    this.person = new Person();
    this.artist = new Artist();
    this.birthDate = this.deathDate = null;
    this.isArtist = false;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
    this.isEditSelected = false;

    this.instrumentList = new Array<ArtistInstrument>();
    this.artistActivityList = new Array<ArtistActivity>();

    this.toDeleteActivities = [];
    this.toDeleteInstruments = [];

    this.isBandClicked = false;
  }

  searchBirthPlace() {
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


  artistEventHandler($event: any) {
    this.selectedArtist = $event;
    this.firstNames = this.selectedArtist.firstNames;
    this.lastName = this.selectedArtist.lastName;
    this.stageName = this.selectedArtist.stageName;
    if (this.selectedArtist.stageName) {
      this.isArtist = true;
      this.stageName = this.selectedArtist.stageName;
    }
    this.person.setBirthDate(this.selectedArtist.birthDate);
    this.person.setDeathDate(this.selectedArtist.deathDate);
    this.person.setOrigin(this.selectedArtist.origin);

    this.artistInstrumentService.getByArtistId(this.selectedArtist.id).subscribe(
      res => {
        console.log(res);
        this.instrumentList = res;
      }
    );

    this.artistActivityService.getByArtistId(this.selectedArtist.id).subscribe(
      res => {
        console.log(res);
        this.artistActivityList = res;
      }
    );

    this.isEditSelected = true;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
    if (this.person.origin) {
      this.isBirthPlaceSelected = true;
    } else {
      this.isBirthPlaceSelected = false;
    }
  }


  searchEventHandler($event) {
    this.selectedArtist = null;
  }

  searchValue(value: boolean): boolean {
    this.resetClicked();
    return !value;
  }

  resetClicked() {
    this.isInstrumentClicked = false;
    this.isBandClicked = false;
  }



  delete() {
    this.personService.delete(this.selectedArtist.id).subscribe(
      res => {
        console.log(res);
        this.reset();
      },
      err => {
        window.alert('Error has occured');
      }
    );
  }

  update() {
    if ( this.firstNames === this.selectedArtist.firstNames && this.lastName === this.selectedArtist.lastName && this.birthDate !== null && this.birthDate === this.selectedArtist.birthDate && this.deathDate !== null && this.deathDate === this.selectedArtist.deathDate) {
      window.alert('You need to do some changes before update');
    } else {
      this.person.id = this.selectedArtist.id;
      this.person.setFirstNames(this.firstNames);
      this.person.setLastName(this.lastName);
      if (this.birthDate) {
        this.person.setBirthDate(this.birthDate);
      }
      if (this.deathDate) {
        this.person.setDeathDate(this.deathDate);
      }
      if (this.isArtist) {

        this.artist = new Artist(this.person);
        this.artist.setStageName(this.stageName);

        this.artistService.edit(this.artist).subscribe(
          res => {

            // Edycja instrumentów

            this.instrumentList.forEach(el => {

              if (this.toDeleteInstruments.indexOf(el.id) === -1) {
              el.artist = this.artist;
              this.artistInstrumentService.edit(el).subscribe(
                res => {
                  console.log('add-song-component received:');
                  console.log(res);
                },
                err => {
                  err => console.error(err);
                }
              );
              }
            });

            // Edycja zespołów

            this.artistActivityList.forEach(
              el => {

                if (this.toDeleteActivities.indexOf(el.id) === -1) {
                el.artist = this.artist;
                this.artistActivityService.edit(el).subscribe(
                  res => {
                    console.log('add-song-component received:');
                    console.log(res);
                  },
                  err => {
                    err => console.error(err);
                  }
                );
                }
              }
            );
            this.toDeleteActivities.forEach(el => {
              this.artistActivityService.delete(el).subscribe(
                el => {
                  console.log('deleted');
                }
              );
            });

            this.toDeleteInstruments.forEach(el => {
              this.artistInstrumentService.delete(el).subscribe(
                el => {
                  console.log('deleted');
                }
              );
            });


            console.log(res);
            this.reset();
          },
          err => {
            window.alert('Error has occurred');
          }
        );

      } else {
      this.personService.edit(this.person).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occurred');
        }
      );
      }
    }
  }

  reset() {
    this.firstNames = this.lastName = this.stageName = '';
    this.person = new Person();
    this.artist = new Artist();
    this.birthDate = this.deathDate = null;
    this.isArtist = false;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
    this.isEditSelected = false;
  }


  addRole(artistActivity: ArtistActivity) {
    if (artistActivity.tempRole !== '') {
      this.roles = artistActivity.roles;
      this.roles.push(artistActivity.tempRole);
      artistActivity.roles = this.roles;
      console.log(this.roles);
      console.log(artistActivity.roles);
      artistActivity.tempRole = '';
      this.roles = [];
    }
  }

  removeInstrument(instrument: ArtistInstrument) {
    this.toDeleteInstruments.push(instrument.id);
    const index = this.instrumentList.indexOf(instrument);
    if (index > -1) { this.instrumentList.splice(index, 1); }
  }

  removeArtistActivity(activity: ArtistActivity) {
    this.toDeleteActivities.push(activity.id);
    const index = this.artistActivityList.indexOf(activity);
    if (index > -1) { this.artistActivityList.splice(index, 1); }
  }

  removeRole(artistActivity: ArtistActivity, role: string) {
    this.roles = artistActivity.roles;
    const index = this.roles.indexOf(role, 0);
    if (index > -1) {
      this.roles.splice(index, 1);
    }
    artistActivity.roles = this.roles;
    this.roles = [];

  }



}
