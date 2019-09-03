import { TestBed } from "@angular/core/testing";

import { SongAuthorshipshipService } from "./song-authorship.service";

describe("SongAuthorshipService", () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it("should be created", () => {
    const service: SongAuthorshipService = TestBed.get(SongAuthorshipService);
    expect(service).toBeTruthy();
  });
});
