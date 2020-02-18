import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInstrumentTypeComponent } from './edit-instrument-type.component';

describe('EditInstrumentTypeComponent', () => {
  let component: EditInstrumentTypeComponent;
  let fixture: ComponentFixture<EditInstrumentTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditInstrumentTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInstrumentTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
