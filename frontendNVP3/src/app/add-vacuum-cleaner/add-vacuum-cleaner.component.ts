import { Component } from '@angular/core';
import {CreateUserService} from "../services/create-user.service";
import {VacuumeCleanerService} from "../services/vacuume-cleaner.service";

@Component({
  selector: 'app-add-vacuum-cleaner',
  templateUrl: './add-vacuum-cleaner.component.html',
  styleUrls: ['./add-vacuum-cleaner.component.css']
})
export class AddVacuumCleanerComponent {
  name : string = "";

  constructor(private vacuumCleanerService: VacuumeCleanerService) {}


  onSubmit() {
      this.vacuumCleanerService.addVacuumCleaners(this.name).subscribe((response) => {
        console.log(response);
      });
      this.clearForm();
  }

  clearForm() {
    this.name = "";
  }


}
