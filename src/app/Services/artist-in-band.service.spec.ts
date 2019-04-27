import { TestBed } from '@angular/core/testing';

import { ArtistInBandService } from './artist-in-band.service';

describe('ArtistInBandService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArtistInBandService = TestBed.get(ArtistInBandService);
    expect(service).toBeTruthy();
  });
});
