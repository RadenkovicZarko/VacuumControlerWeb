import { TestBed } from '@angular/core/testing';

import { VacuumeCleanerService } from './vacuume-cleaner.service';

describe('VacuumeCleanerService', () => {
  let service: VacuumeCleanerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacuumeCleanerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
