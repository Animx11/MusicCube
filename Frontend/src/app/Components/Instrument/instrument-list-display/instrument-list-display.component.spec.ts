import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstrumentListDisplayComponent } from './instrument-list-display.component';

describe('InstrumentListDisplayComponent', () => {
  let component: InstrumentListDisplayComponent;
  let fixture: ComponentFixture<InstrumentListDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstrumentListDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstrumentListDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
