import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacuumCleanerTableComponent } from './vacuum-cleaner-table.component';

describe('VacuumCleanerTableComponent', () => {
  let component: VacuumCleanerTableComponent;
  let fixture: ComponentFixture<VacuumCleanerTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VacuumCleanerTableComponent]
    });
    fixture = TestBed.createComponent(VacuumCleanerTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
