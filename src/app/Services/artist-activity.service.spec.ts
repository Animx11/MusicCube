import { TestBed } from '@angular/core/testing';

import { ArtistActivityService } from './artist-activity.service';

describe('ArtistActivityService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArtistActivityService = TestBed.get(ArtistActivityService);
    expect(service).toBeTruthy();
  });
});
