import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVacuumCleanerComponent } from './add-vacuum-cleaner.component';

describe('AddVacuumCleanerComponent', () => {
  let component: AddVacuumCleanerComponent;
  let fixture: ComponentFixture<AddVacuumCleanerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddVacuumCleanerComponent]
    });
    fixture = TestBed.createComponent(AddVacuumCleanerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
