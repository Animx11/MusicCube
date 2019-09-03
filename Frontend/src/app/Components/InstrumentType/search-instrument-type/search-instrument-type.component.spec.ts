import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInstrumentTypeComponent } from './search-instrument-type.component';

describe('SearchInstrumentTypeComponent', () => {
  let component: SearchInstrumentTypeComponent;
  let fixture: ComponentFixture<SearchInstrumentTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchInstrumentTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchInstrumentTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
