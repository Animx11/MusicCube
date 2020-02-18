import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreListDisplayComponent } from './genre-list-display.component';

describe('GenreListDisplayComponent', () => {
  let component: GenreListDisplayComponent;
  let fixture: ComponentFixture<GenreListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenreListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenreListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
