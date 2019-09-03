import { TestBed } from '@angular/core/testing';

import { ArtistInstrumentService } from './artist-instrument.service';

describe('ArtistInstrumentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArtistInstrumentService = TestBed.get(ArtistInstrumentService);
    expect(service).toBeTruthy();
  });
});
