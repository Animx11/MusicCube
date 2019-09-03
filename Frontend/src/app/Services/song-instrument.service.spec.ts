import { TestBed } from '@angular/core/testing';

import { SongInstrumentService } from './song-instrument.service';

describe('SongInstrumentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SongInstrumentService = TestBed.get(SongInstrumentService);
    expect(service).toBeTruthy();
  });
});
