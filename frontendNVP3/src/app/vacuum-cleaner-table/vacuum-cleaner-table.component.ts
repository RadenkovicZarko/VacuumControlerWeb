import { Component } from '@angular/core';
import {VacuumCleaner} from "../model";
import {VacuumeCleanerService} from "../services/vacuume-cleaner.service";
import {MatDialog} from "@angular/material/dialog";
import {ErrorDialogComponent} from "../error-dialog/error-dialog.component";

@Component({
  selector: 'app-vacuum-cleaner-table',
  templateUrl: './vacuum-cleaner-table.component.html',
  styleUrls: ['./vacuum-cleaner-table.component.css']
})
export class VacuumCleanerTableComponent {
  vacuumCleaners: VacuumCleaner[] = [];
  searchCriteria: any = {
    statusList: [] // Initialize as an empty array for multiple selections
  };
  vacuumCleanerIds: number[] = [];
  selectedId: number = 0;
  selectedActivity: string = '';
  selectedDate: Date = new Date();
  selectedTime: string = '';

  constructor(private vacuumCleanerService: VacuumeCleanerService,private dialog: MatDialog,) {}

  ngOnInit() {
    this.vacuumCleanerService.getForUser().subscribe(
      (data) => {
        this.vacuumCleaners = data;
        this.extractIdsFromVacuumCleaners();
      },
      (error) => {

      }
    );

  }

  extractIdsFromVacuumCleaners(): void {
    this.vacuumCleanerIds = this.vacuumCleaners.map(vc => vc.id);
  }

  startVacuumCleaner(vacuumCleanerId: number) {
    this.vacuumCleanerService.startVacuumCleaner(vacuumCleanerId).subscribe(
      (data) => {
        this.openErrorDialog("Starting vacuum cleaner","Success");
        setTimeout(() => {
          this.refreshPage();
        }, 15000);
      },
      (error) => {
        this.openErrorDialog("Error starting vacuum cleaner","Error");
      }
    );
  }
  stopVacuumCleaner(vacuumCleanerId: number) {
    this.vacuumCleanerService.stopVacuumCleaner(vacuumCleanerId).subscribe(
      (data) => {
        this.openErrorDialog("Stopping vacuum cleaner","Success");
        setTimeout(() => {
          this.refreshPage();
        }, 15000);
      },
      (error) => {
        this.openErrorDialog("Error stoping vacuum cleaner","Error");
      }
    );
  }
  dischargeVacuumCleaner(vacuumCleanerId: number) {
    this.vacuumCleanerService.dischargeVacuumCleaner(vacuumCleanerId).subscribe(
      (data) => {
        this.openErrorDialog("Discharging vacuum cleaner","Success");
        setTimeout(() => {
          this.refreshPage();
        }, 15000);
      },
      (error) => {
        this.openErrorDialog("Error discharging vacuum cleaner","Error");
      }
    );
  }

  removeVacuumCleaner(vacuumCleanerId: number) {
    this.vacuumCleanerService.deleteVacuumCleaner(vacuumCleanerId).subscribe(
      (data) => {
        this.openErrorDialog("Removing vacuum cleaner","Success");
        setTimeout(() => {
          this.refreshPage();
        });
      },
      (error) => {
        this.openErrorDialog("Error removing vacuum cleaner","Error");
      }
    );
  }


  openErrorDialog(message: string,heading: string): void {
    const dialogRef = this.dialog.open(ErrorDialogComponent, {
      width: '300px',
      data: { errorMessage: message ,
        heading: heading}
    });
  }

  refreshPage(): void {
    window.location.reload();
  }


  scheduleActivity(): void {
    const dateTimeString = `${this.selectedDate}T${this.selectedTime}`; // Combine date and time
    const requestData = {
      id: this.selectedId,
      action: this.selectedActivity,
      dateTimeString: dateTimeString // Combine date and time in ISO format
    };
    if(this.selectedId==0 || this.selectedId==null || this.selectedActivity=='' || dateTimeString=='')
      this.openErrorDialog("Error scheduing vacuum cleaner","Error");
    else {
      this.vacuumCleanerService.scheduleVacuumCleaner(requestData).subscribe();
      this.openErrorDialog("Scheduling vacuum cleaner", "Success");
    }
  }
}
