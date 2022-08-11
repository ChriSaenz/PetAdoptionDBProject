import { TestBed } from '@angular/core/testing';

import { AuthguardTestService } from './authguard-test.service';

describe('AuthguardTestService', () => {
  let service: AuthguardTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthguardTestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
