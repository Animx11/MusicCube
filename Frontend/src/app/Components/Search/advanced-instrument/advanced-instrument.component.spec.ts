import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedInstrumentComponent } from './advanced-instrument.component';

describe('AdvancedInstrumentComponent', () => {
  let component: AdvancedInstrumentComponent;
  let fixture: ComponentFixture<AdvancedInstrumentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedInstrumentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedInstrumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
