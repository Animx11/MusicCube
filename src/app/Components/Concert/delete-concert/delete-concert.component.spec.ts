import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteConcertComponent } from './delete-concert.component';

describe('DeleteConcertComponent', () => {
  let component: DeleteConcertComponent;
  let fixture: ComponentFixture<DeleteConcertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteConcertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteConcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
