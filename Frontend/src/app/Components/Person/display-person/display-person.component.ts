import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { PersonService } from '../../../Services/person.service';
import {Person} from '../../../Class/Person';

@Component({
  selector: 'app-display-person',
  templateUrl: './display-person.component.html',
  styleUrls: ['./display-person.component.css']
})
export class DisplayPersonComponent implements OnInit {

  person: Person;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private personService: PersonService) { }

  ngOnInit() {
    this.getPerson();
  }

  private getPerson() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.personService.getById(id).subscribe(
      res => {
        this.person = new Person(res);
        console.log('display-person-component received: ', res);
      },
      err => console.error(err));
  }
}
