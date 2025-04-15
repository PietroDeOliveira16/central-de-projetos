import { TestBed } from '@angular/core/testing';

import { LandPageService } from './land-page.service';

describe('LandPageService', () => {
  let service: LandPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LandPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
