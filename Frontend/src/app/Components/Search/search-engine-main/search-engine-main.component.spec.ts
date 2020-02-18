import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchEngineMainComponent } from './search-engine-main.component';

describe('SearchEngineMainComponent', () => {
  let component: SearchEngineMainComponent;
  let fixture: ComponentFixture<SearchEngineMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchEngineMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchEngineMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
