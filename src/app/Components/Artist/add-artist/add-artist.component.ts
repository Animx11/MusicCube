import { Component, OnInit, Input } from '@angular/core';

import { Artist } from 'src/app/Class/Artist';
import { ArtistService } from 'src/app/Services/artist.service';
import { Person } from 'src/app/Class/Person';
import { PersonService } from 'src/app/Services/person.service';

@Component({
  selector: 'app-add-artist',
  templateUrl: './add-artist.component.html',
  styleUrls: ['./add-artist.component.css']
})
export class AddArtistComponent implements OnInit {
  person: Person;
  artist: Artist;
  firstNames: string;
  lastName: string;
  stageName: string;
  birthDate: Date;
  deathDate: Date;
  isArtist: boolean;
  isBirthPlaceClicked: boolean;
  isBirthPlaceSelected: boolean;

  constructor(private artistService: ArtistService, private personService: PersonService) {}

  ngOnInit() {
    this.firstNames = this.lastName = this.stageName = '';
    this.person = new Person();
    this.birthDate = this.deathDate = null;
    this.isArtist = false;
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = false;
  }

  searchBirthPlace(){
    this.isBirthPlaceClicked = true;
  }

  cityEventHandler($event: any) {
    this.person.setOrigin($event);
    this.isBirthPlaceClicked = false;
    this.isBirthPlaceSelected = true;
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
}
