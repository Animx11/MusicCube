import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayLocalizationComponent } from './display-localization.component';

describe('DisplayLocalizationComponent', () => {
  let component: DisplayLocalizationComponent;
  let fixture: ComponentFixture<DisplayLocalizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayLocalizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
