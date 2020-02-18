import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { UserManage } from 'src/app/Class/UserManage';
import { UserService } from 'src/app/Services/user.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {


  private searchOn: boolean;
  private listOn: boolean;
  private reachedLastPage: boolean;
  private pageCount: number;

  users$: Observable<UserManage[]>;
  userPage: UserManage[];
  private searchTerms = new Subject<string>();

  @Output() userManageEvent = new EventEmitter<UserManage>();
  @Output() searchEvent = new EventEmitter();

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.pageCount = 0;
    this.users$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.userService.getUserByUserName(term))
    );
  }

  toggleSearch() {
    this.users$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.userService.getUserByUserName(term))
    );
    this.searchEvent.emit();
  }

  search(term: string): void {
    if (term !== '' && term !== ' ') {
     this.searchTerms.next(term);
      this.users$.forEach(el => {el.forEach(res => {console.log(res)})});
    }
  }

  onSelect(userManage: UserManage) {
    this.userManageEvent.emit(userManage);
  }
  
  makeAdmin(id: number) {
    this.userService.editRole(id, "Admin").subscribe(res => {
      console.log(res);
    },
    err => {
      console.log(err);
    });
  }

  makeUser(id: number) {

    this.userService.editRole(id, "User").subscribe(res => {
      console.log(res);
    },
    err => {
      console.log(err);
    });
  }
}
