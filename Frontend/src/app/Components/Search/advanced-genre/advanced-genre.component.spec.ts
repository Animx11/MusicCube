import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedGenreComponent } from './advanced-genre.component';

describe('AdvancedGenreComponent', () => {
  let component: AdvancedGenreComponent;
  let fixture: ComponentFixture<AdvancedGenreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedGenreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedGenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
