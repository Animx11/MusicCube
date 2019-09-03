import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { PersonService } from 'src/app/Services/person.service';
import { Person } from 'src/app/Class/Person';

@Component({
  selector: 'app-search-person',
  templateUrl: './search-person.component.html',
  styleUrls: ['./search-person.component.css']
})
export class SearchPersonComponent implements OnInit {
  private searchOn: boolean;

  persons$: Observable<Person[]>;
  private searchTerms = new Subject<string>();

  @Output() personEvent = new EventEmitter<Person>();
  @Output() searchEvent = new EventEmitter();


  constructor(private personService: PersonService) {
    this.searchOn = false;
  }

  ngOnInit(): void {
    this.persons$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.personService.getByLastName(term))
    );
  }

  toggleSearch() {
    this.persons$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.personService.getByLastName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
    }
  }

  onSelect(person: Person) {
    this.personEvent.emit(person);
  }
}
