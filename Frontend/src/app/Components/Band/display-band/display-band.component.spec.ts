import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayBandComponent } from './display-band.component';

describe('DisplayBandComponent', () => {
  let component: DisplayBandComponent;
  let fixture: ComponentFixture<DisplayBandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayBandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayBandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
