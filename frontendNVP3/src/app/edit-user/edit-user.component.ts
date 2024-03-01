import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {User} from "../model";
import {EditUserService} from "../services/edit-user.service";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent {
  // user : User = {} as User
  id : number = 0
  user : any = {id: 0,firstName: '', lastName: '', email: '',password:'', permissions: {}}
  originalUser : User = {} as User
  constructor(private route: ActivatedRoute, private editUserService: EditUserService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.editUserService.getUserById(this.id).subscribe(data => {
        this.originalUser = data;
        this.user.firstName = this.originalUser.firstName
        this.user.lastName = this.originalUser.lastName
        this.user.email = this.originalUser.email
        this.user.password = this.originalUser.password
        this.user.id = this.originalUser.id
        for(const permision of this.originalUser.permissions)
        {
          if(permision.name=="can_create_users")
            this.user.permissions.can_create_users = true
          if(permision.name=="can_read_users")
            this.user.permissions.can_read_users = true
          if(permision.name=="can_update_users")
            this.user.permissions.can_update_users = true
          if(permision.name=="can_delete_users")
            this.user.permissions.can_delete_users = true
          if(permision.name=="can_search_vacuum")
            this.user.permissions.can_search_vacuum = true
          if(permision.name=="can_start_vacuum")
            this.user.permissions.can_start_vacuum = true
          if(permision.name=="can_stop_vacuum")
            this.user.permissions.can_stop_vacuum = true
          if(permision.name=="can_discharge_vacuum")
            this.user.permissions.can_discharge_vacuum = true
          if(permision.name=="can_add_vacuum")
            this.user.permissions.can_add_vacuum = true
          if(permision.name=="can_remove_vacuums")
            this.user.permissions.can_remove_vacuums = true

        }
      });
    });
  }


  saveChanges(): void {
    const permissionsArray: { id: string, name: string }[] = [];
    const permissionsSave = this.user.permissions

      if (this.user.permissions.can_create_users) {
        permissionsArray.push({id: "2", name: "can_create_users"});
      }
      if (this.user.permissions.can_read_users) {
        permissionsArray.push({id: "1", name: "can_read_users"});
      }
      if (this.user.permissions.can_update_users) {
        permissionsArray.push({id: "3", name: "can_update_users"});
      }
      if (this.user.permissions.can_delete_users) {
        permissionsArray.push({id: "4", name: "can_delete_users"});
      }
    if (this.user.permissions.can_search_vacuum) {
      permissionsArray.push({id: "2", name: "can_search_vacuum"});
    }
    if (this.user.permissions.can_start_vacuum) {
      permissionsArray.push({id: "1", name: "can_start_vacuum"});
    }
    if (this.user.permissions.can_stop_vacuum) {
      permissionsArray.push({id: "3", name: "can_stop_vacuum"});
    }
    if (this.user.permissions.can_discharge_vacuum) {
      permissionsArray.push({id: "4", name: "can_discharge_vacuum"});
    }
    if (this.user.permissions.can_add_vacuum) {
      permissionsArray.push({id: "2", name: "can_add_vacuum"});
    }
    if (this.user.permissions.can_remove_vacuums) {
      permissionsArray.push({id: "1", name: "can_remove_vacuums"});
    }


      this.user.permissions = permissionsArray;
      this.editUserService.updateUser(this.user).subscribe((response) => {
        console.log(response);
      });
      this.user.permissions=permissionsSave

  }
}
