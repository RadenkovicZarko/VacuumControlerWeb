import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchVacuumCleanerComponent } from './search-vacuum-cleaner.component';

describe('SearchVacuumCleanerComponent', () => {
  let component: SearchVacuumCleanerComponent;
  let fixture: ComponentFixture<SearchVacuumCleanerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchVacuumCleanerComponent]
    });
    fixture = TestBed.createComponent(SearchVacuumCleanerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
