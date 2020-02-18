import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumListDisplayComponent } from './album-list-display.component';

describe('AlbumListDisplayComponent', () => {
  let component: AlbumListDisplayComponent;
  let fixture: ComponentFixture<AlbumListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlbumListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbumListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
