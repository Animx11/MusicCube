import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAlbumDetailsComponent } from './edit-album-details.component';

describe('EditAlbumDetailsComponent', () => {
  let component: EditAlbumDetailsComponent;
  let fixture: ComponentFixture<EditAlbumDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAlbumDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAlbumDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
