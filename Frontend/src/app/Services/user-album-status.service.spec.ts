import { TestBed } from '@angular/core/testing';

import { UserAlbumStatusService } from './user-album-status.service';

describe('UserAlbumStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserAlbumStatusService = TestBed.get(UserAlbumStatusService);
    expect(service).toBeTruthy();
  });
});
