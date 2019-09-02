import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedAlbumComponent } from './advanced-album.component';

describe('AdvancedAlbumComponent', () => {
  let component: AdvancedAlbumComponent;
  let fixture: ComponentFixture<AdvancedAlbumComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedAlbumComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedAlbumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
