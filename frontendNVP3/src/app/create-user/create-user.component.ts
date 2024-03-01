import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../services/login.service";
import {MatDialog} from "@angular/material/dialog";
import {CreateUserService} from "../services/create-user.service";


@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {

  user: any = { firstName: '', lastName: '', email: '', password: '', permissions: [] };


  constructor(private createUserService: CreateUserService) {}

  onSubmit() {
    const permissionsArray: { id: string, name: string }[] = [];

    if (Array.isArray(this.user.permissions)) {
      if (this.user.permissions.can_create_users) {
        permissionsArray.push({ id: "1", name: "can_create_users" });
      }
      if (this.user.permissions.can_read_users) {
        permissionsArray.push({ id: "2", name: "can_read_users" });
      }
      if (this.user.permissions.can_update_users) {
        permissionsArray.push({ id: "3", name: "can_update_users" });
      }
      if (this.user.permissions.can_delete_users) {
        permissionsArray.push({ id: "4", name: "can_delete_users" });
      }

      this.user.permissions = permissionsArray;
      this.createUserService.createUser(this.user).subscribe((response) => {
        console.log(response);
      });
      this.clearForm();
    } else {
      console.error("this.user.permissions is not an array or is undefined");
    }
  }

  clearForm() {
    this.user = { firstName: '', lastName: '', email: '',password:'', permissions: [] }; // Reset form values
  }

}
