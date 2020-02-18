import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSongDetailsComponent } from './edit-song-details.component';

describe('EditSongDetailsComponent', () => {
  let component: EditSongDetailsComponent;
  let fixture: ComponentFixture<EditSongDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditSongDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSongDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
