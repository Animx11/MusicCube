import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteLocalizationComponent } from './delete-localization.component';

describe('DeleteLocalizationComponent', () => {
  let component: DeleteLocalizationComponent;
  let fixture: ComponentFixture<DeleteLocalizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteLocalizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteLocalizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
