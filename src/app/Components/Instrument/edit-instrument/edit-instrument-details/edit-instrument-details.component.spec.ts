import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInstrumentDetailsComponent } from './edit-instrument-details.component';

describe('EditInstrumentDetailsComponent', () => {
  let component: EditInstrumentDetailsComponent;
  let fixture: ComponentFixture<EditInstrumentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditInstrumentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInstrumentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
