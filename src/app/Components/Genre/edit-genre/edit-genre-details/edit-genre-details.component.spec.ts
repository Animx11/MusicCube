import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGenreDetailsComponent } from './edit-genre-details.component';

describe('EditGenreDetailsComponent', () => {
  let component: EditGenreDetailsComponent;
  let fixture: ComponentFixture<EditGenreDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditGenreDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditGenreDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
