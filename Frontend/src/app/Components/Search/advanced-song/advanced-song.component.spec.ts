import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedSongComponent } from './advanced-song.component';

describe('AdvancedSongComponent', () => {
  let component: AdvancedSongComponent;
  let fixture: ComponentFixture<AdvancedSongComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedSongComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedSongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
