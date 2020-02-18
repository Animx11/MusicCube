import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditConcertDetailsComponent } from './edit-concert-details.component';

describe('EditConcertDetailsComponent', () => {
  let component: EditConcertDetailsComponent;
  let fixture: ComponentFixture<EditConcertDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditConcertDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditConcertDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
