import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayConcertComponent } from './display-concert.component';

describe('DisplayConcertComponent', () => {
  let component: DisplayConcertComponent;
  let fixture: ComponentFixture<DisplayConcertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayConcertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayConcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
