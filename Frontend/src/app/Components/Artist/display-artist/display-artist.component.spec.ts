import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayArtistComponent } from './display-artist.component';

describe('DisplayArtistComponent', () => {
  let component: DisplayArtistComponent;
  let fixture: ComponentFixture<DisplayArtistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayArtistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayArtistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
