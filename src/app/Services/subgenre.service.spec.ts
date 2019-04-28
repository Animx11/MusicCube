import { TestBed } from '@angular/core/testing';

import { SubgenreService } from './subgenre.service';

describe('SubgenreService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SubgenreService = TestBed.get(SubgenreService);
    expect(service).toBeTruthy();
  });
});
