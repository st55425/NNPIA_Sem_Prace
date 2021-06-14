import { TestBed } from '@angular/core/testing';

import { AuthHtppInterceptorServiceService } from './auth-htpp-interceptor-service.service';

describe('AuthHtppInterceptorServiceService', () => {
  let service: AuthHtppInterceptorServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthHtppInterceptorServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
