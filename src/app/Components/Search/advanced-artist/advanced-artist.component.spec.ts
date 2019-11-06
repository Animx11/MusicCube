import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedArtistComponent } from './advanced-artist.component';

describe('AdvancedArtistComponent', () => {
  let component: AdvancedArtistComponent;
  let fixture: ComponentFixture<AdvancedArtistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedArtistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedArtistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
