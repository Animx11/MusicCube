import { TestBed } from '@angular/core/testing';

import { UserSongStatusService } from './user-song-status.service';

describe('UserSongStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserSongStatusService = TestBed.get(UserSongStatusService);
    expect(service).toBeTruthy();
  });
});
