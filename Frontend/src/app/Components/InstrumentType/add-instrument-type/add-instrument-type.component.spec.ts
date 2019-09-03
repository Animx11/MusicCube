import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInstrumentTypeComponent } from './add-instrument-type.component';

describe('AddInstrumentTypeComponent', () => {
  let component: AddInstrumentTypeComponent;
  let fixture: ComponentFixture<AddInstrumentTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddInstrumentTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInstrumentTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
