import { TestBed } from '@angular/core/testing';

import { SongAuthorService } from './song-author.service';

describe('SongAuthorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SongAuthorService = TestBed.get(SongAuthorService);
    expect(service).toBeTruthy();
  });
});
