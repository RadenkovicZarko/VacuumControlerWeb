import { Component } from '@angular/core';
import {VacuumeCleanerService} from "../services/vacuume-cleaner.service";
import {VacuumCleaner} from "../model";
import {ErrorDialogComponent} from "../error-dialog/error-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-search-vacuum-cleaner',
  templateUrl: './search-vacuum-cleaner.component.html',
  styleUrls: ['./search-vacuum-cleaner.component.css']
})
export class SearchVacuumCleanerComponent {
  vacuumCleaners: VacuumCleaner[] = [];
  searchCriteria: any = {
    statusList: [] // Initialize as an empty array for multiple selections
  };

  constructor(private vacuumCleanerService: VacuumeCleanerService,private dialog: MatDialog,) {}

  searchVacuumCleaners() {
    this.vacuumCleanerService.searchVacuumCleaners(this.searchCriteria).subscribe(
      (data: VacuumCleaner[]) => {
        this.vacuumCleaners = data;
      },
      (error) => {
        console.error('Error fetching vacuum cleaners:', error);
      }
    );
  }


}
