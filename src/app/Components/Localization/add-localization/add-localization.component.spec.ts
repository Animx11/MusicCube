import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLocalizationComponent } from './add-localization.component';

describe('AddLocalizationComponent', () => {
  let component: AddLocalizationComponent;
  let fixture: ComponentFixture<AddLocalizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLocalizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
