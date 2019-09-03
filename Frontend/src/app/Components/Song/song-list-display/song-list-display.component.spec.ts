import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SongListDisplayComponent } from './song-list-display.component';

describe('SongListDisplayComponent', () => {
  let component: SongListDisplayComponent;
  let fixture: ComponentFixture<SongListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SongListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SongListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
