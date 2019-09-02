import { Component, OnInit } from '@angular/core';
import { ArtistService } from 'src/app/Services/artist.service';
import { PersonService } from 'src/app/Services/person.service';
import { Person } from 'src/app/Class/Person';
import { Artist } from 'src/app/Class/Artist';
import { isArray } from 'util';

@Component({
  selector: 'app-edit-artist',
  templateUrl: './edit-artist.component.html',
  styleUrls: ['./edit-artist.component.css']
})
export class EditArtistComponent implements OnInit {

  private person: Person;
  private artist: Artist;
  private selectedArtist: Artist;
  private firstNames: string;
  private lastName: string;
  private stageName: string;
  private birthDate: Date;
  private deathDate: Date;
  private isArtist: boolean;
  private isBirthPlaceClicked: boolean;
  private isBirthPlaceSelected: boolean;

  private isEditSelected: boolean;

  constructor(private artistService: ArtistService, private personService: PersonService) { }
  
  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = '';
    this.person = new Person();
    this.artist = new Artist();
    this.birthDate = this.deathDate = null;
    this.isArtist = false;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
    this.isEditSelected = false;
  }

  searchBirthPlace() {
    this.isBirthPlaceClicked = true;
  }

  cityEventHandler($event: any) {
    this.person.setOrigin($event);
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = true;
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
    if( this.firstNames === this.selectedArtist.firstNames && this.lastName === this.selectedArtist.lastName && this.birthDate !== null && this.birthDate === this.selectedArtist.birthDate && this.deathDate !== null && this.deathDate === this.selectedArtist.deathDate ) {
      window.alert('You need to do some changes before update');
    } else {
      this.person.id = this.selectedArtist.id;
      this.person.setFirstNames(this.firstNames);
      this.person.setLastName(this.lastName);
      if(this.birthDate) {
        this.person.setBirthDate(this.birthDate);
      }
      if(this.deathDate) {
        this.person.setDeathDate(this.deathDate);
      }
      if(this.isArtist) {
        this.artist = new Artist(this.person);
        this.artist.setStageName(this.stageName);
        this.artistService.edit(this.artist).subscribe(
          res => {
            console.log(res);
            this.reset();
          },
          err => {
            window.alert('Error has occured');
          }
        );
      } else {
      this.personService.edit(this.person).subscribe(
        res => {
          console.log(res);
          this.reset();
        },
        err => {
          window.alert('Error has occured');
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



}
