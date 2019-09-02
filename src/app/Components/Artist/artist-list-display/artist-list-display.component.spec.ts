import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistListDisplayComponent } from './artist-list-display.component';

describe('ArtistListDisplayComponent', () => {
  let component: ArtistListDisplayComponent;
  let fixture: ComponentFixture<ArtistListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArtistListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
