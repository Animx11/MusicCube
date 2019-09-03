import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedBandComponent } from './advanced-band.component';

describe('AdvancedBandComponent', () => {
  let component: AdvancedBandComponent;
  let fixture: ComponentFixture<AdvancedBandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedBandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedBandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
