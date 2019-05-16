import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLocalizationDetailsComponent } from './edit-localization-details.component';

describe('EditLocalizationDetailsComponent', () => {
  let component: EditLocalizationDetailsComponent;
  let fixture: ComponentFixture<EditLocalizationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLocalizationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLocalizationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
