import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandListDisplayComponent } from './band-list-display.component';

describe('BandListDisplayComponent', () => {
  let component: BandListDisplayComponent;
  let fixture: ComponentFixture<BandListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
