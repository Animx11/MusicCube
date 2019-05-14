import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchLocalizationComponent } from './search-localization.component';

describe('SearchLocalizationComponent', () => {
  let component: SearchLocalizationComponent;
  let fixture: ComponentFixture<SearchLocalizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchLocalizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
