import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInstrumentComponent } from './search-instrument.component';

describe('SearchInstrumentComponent', () => {
  let component: SearchInstrumentComponent;
  let fixture: ComponentFixture<SearchInstrumentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchInstrumentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchInstrumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
