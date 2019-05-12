import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLocalizationComponent } from './edit-localization.component';

describe('EditLocalizationComponent', () => {
  let component: EditLocalizationComponent;
  let fixture: ComponentFixture<EditLocalizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLocalizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
