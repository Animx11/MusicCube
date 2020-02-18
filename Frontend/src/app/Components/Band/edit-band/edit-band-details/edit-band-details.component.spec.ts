import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBandDetailsComponent } from './edit-band-details.component';

describe('EditBandDetailsComponent', () => {
  let component: EditBandDetailsComponent;
  let fixture: ComponentFixture<EditBandDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditBandDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBandDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
