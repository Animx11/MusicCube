import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditArtistDetailsComponent } from './edit-artist-details.component';

describe('EditArtistDetailsComponent', () => {
  let component: EditArtistDetailsComponent;
  let fixture: ComponentFixture<EditArtistDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditArtistDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditArtistDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
