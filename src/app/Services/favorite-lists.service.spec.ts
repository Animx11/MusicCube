import { TestBed } from '@angular/core/testing';

import { FavoriteListsService } from './favorite-lists.service';

describe('FavoriteListsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FavoriteListsService = TestBed.get(FavoriteListsService);
    expect(service).toBeTruthy();
  });
});
