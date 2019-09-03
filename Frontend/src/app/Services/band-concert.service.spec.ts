import { TestBed } from '@angular/core/testing';

import { BandConcertService } from './band-concert.service';

describe('BandConcertService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BandConcertService = TestBed.get(BandConcertService);
    expect(service).toBeTruthy();
  });
});
